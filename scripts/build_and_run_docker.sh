#!/bin/bash

set -e

# Colores para la salida
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
RED='\033[0;31m'
NC='\033[0m' # No Color

# Buscar la raíz del proyecto (donde está el pom.xml principal)
find_project_root() {
    local current_dir="$(pwd)"
    while [[ "$current_dir" != "/" ]]; do
        if [[ -f "$current_dir/pom.xml" && -d "$current_dir/boot" && -d "$current_dir/service" ]]; then
            echo "$current_dir"
            return 0
        fi
        current_dir="$(dirname "$current_dir")"
    done
    echo ""
    return 1
}

PROJECT_ROOT=$(find_project_root)

if [[ -z "$PROJECT_ROOT" ]]; then
    echo -e "${RED}❌ No se pudo encontrar la raíz del proyecto con pom.xml y las carpetas de módulos.${NC}"
    echo -e "${YELLOW}Directorios en la ubicación actual:${NC}"
    ls -la
    exit 1
fi

echo -e "${GREEN}✅ Raíz del proyecto encontrada: $PROJECT_ROOT${NC}"
cd "$PROJECT_ROOT"

echo -e "${YELLOW}📊 Estructura del proyecto:${NC}"
find . -maxdepth 2 -type d | sort

echo -e "${YELLOW}🔨 Compilando la aplicación con Maven...${NC}"
mvn clean package -DskipTests

# Verificar que se generó el JAR
JAR_PATTERN="boot*.jar"
echo -e "${YELLOW}🔍 Buscando el JAR compilado...${NC}"
BOOT_JAR=$(find boot/target -name "$JAR_PATTERN" -type f | head -n 1)

if [[ -z "$BOOT_JAR" ]]; then
    echo -e "${RED}❌ No se encontró el archivo JAR en boot/target. Verificando el nombre exacto del JAR...${NC}"
    find boot/target -name "*.jar" -type f
    BOOT_JAR=$(find boot/target -name "*.jar" -type f | grep -v "sources\|original" | head -n 1)

    if [[ -z "$BOOT_JAR" ]]; then
        echo -e "${RED}❌ No se encontró ningún JAR ejecutable en boot/target.${NC}"
        exit 1
    fi
fi

JAR_NAME=$(basename "$BOOT_JAR")
echo -e "${GREEN}✅ JAR generado: $JAR_NAME${NC}"

# Crear Dockerfile temporal
TEMP_DOCKERFILE="$PROJECT_ROOT/Dockerfile.temp"
echo -e "${YELLOW}📝 Creando Dockerfile temporal adaptado...${NC}"
cat > "$TEMP_DOCKERFILE" << EOF
FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

# Copiar el JAR compilado
COPY $BOOT_JAR /app/bcnc-app.jar

# Variables de entorno
ENV SPRING_PROFILES_ACTIVE=dev
ENV JAVA_OPTS="-Xmx512m"

# Puerto expuesto
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "bcnc-app.jar"]
EOF

# Verificar si podman está instalado, si no, usar docker
if command -v podman &> /dev/null; then
    CONTAINER_CMD="podman"
else
    CONTAINER_CMD="docker"
fi

echo -e "${YELLOW}🐳 Usando $CONTAINER_CMD para la construcción${NC}"

# Construir la imagen
echo -e "${YELLOW}🏗️ Construyendo imagen...${NC}"
$CONTAINER_CMD build -t bcnc-hex-app:local -f "$TEMP_DOCKERFILE" .

# Limpiar el Dockerfile temporal
rm -f "$TEMP_DOCKERFILE"

echo -e "${YELLOW}🧹 Limpiando contenedores previos...${NC}"
$CONTAINER_CMD rm -f bcnc-app-local 2>/dev/null || true

echo -e "${YELLOW}🚀 Ejecutando la aplicación en contenedor...${NC}"
$CONTAINER_CMD run -d --name bcnc-app-local -p 8080:8080 \
    -e SPRING_PROFILES_ACTIVE=dev \
    bcnc-hex-app:local

echo -e "${GREEN}✅ Contenedor iniciado con éxito${NC}"
echo -e "${YELLOW}📋 Mostrando logs (CTRL+C para salir)...${NC}"
$CONTAINER_CMD logs -f bcnc-app-local
