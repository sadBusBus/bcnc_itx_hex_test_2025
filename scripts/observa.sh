#!/bin/bash
set -e

# Colores para una salida más agradable
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
RED='\033[0;31m'
NC='\033[0m' # Sin Color

echo -e "${GREEN}=== Configuración del Perfil de Observabilidad Local ===${NC}"

# Detectar la raíz del proyecto (un nivel arriba del directorio donde está este script)
PROJECT_ROOT="$(cd "$(dirname "$0")/.." && pwd)"
BOOT_DIR="${PROJECT_ROOT}/boot"
BOOT_RESOURCES="${BOOT_DIR}/src/main/resources"
APP_CONFIG="${BOOT_RESOURCES}/application.yml"
LOGBACK_CONFIG="${BOOT_RESOURCES}/logback-spring.xml"

# Ir a la raíz del proyecto para los siguientes comandos
cd "$PROJECT_ROOT"

# Verificar que estamos en la raíz del proyecto
if [ ! -f "pom.xml" ]; then
  echo -e "${RED}ERROR: No se encontró el archivo pom.xml. Asegúrate de ejecutar este script desde la raíz del proyecto${NC}"
  exit 1
fi

echo -e "${YELLOW}[1/3] Comprobando la configuración existente...${NC}"

# Verificamos si el perfil de observabilidad está incluido en el pom.xml
if ! grep -q "<id>observability</id>" pom.xml; then
  echo -e "${RED}ERROR: El perfil 'observability' no está definido en el pom.xml${NC}"
  echo "Por favor, asegúrate de tener el perfil correctamente configurado con las dependencias de métricas."
  exit 1
fi

echo -e "${GREEN}✓ El perfil 'observability' está configurado en el pom.xml${NC}"

# Verificar si tenemos logback-spring.xml
if [ ! -f "$LOGBACK_CONFIG" ]; then
  echo -e "${RED}ERROR: No se encontró el archivo logback-spring.xml en ${LOGBACK_CONFIG}${NC}"
  exit 1
fi

echo -e "${GREEN}✓ Se encontró el archivo logback-spring.xml en ${LOGBACK_CONFIG}${NC}"

# Crear directorio de logs si no existe
echo -e "${YELLOW}[2/3] Preparando directorios para logs...${NC}"
mkdir -p "${PROJECT_ROOT}/logs/archived"

# Verificar y actualizar application.yml para habilitar métricas
echo -e "${YELLOW}[3/3] Verificando la configuración de métricas en application.yml...${NC}"

# Verificar si existe management.endpoints en application.yml
if grep -q "management:" "$APP_CONFIG"; then
  echo -e "${GREEN}✓ La configuración de management ya existe en application.yml${NC}"
else
  echo "Añadiendo configuración de métricas en application.yml..."

  # Crear un archivo temporal
  TEMP_CONFIG="${BOOT_RESOURCES}/application.temp.yml"
  cp "$APP_CONFIG" "$TEMP_CONFIG"

  # Añadir configuración de métricas
  cat >> "$TEMP_CONFIG" << EOF

# Configuración de métricas de observabilidad
management:
  endpoints:
    web:
      exposure:
        include: health,info,metrics
  endpoint:
    health:
      show-details: always
  metrics:
    tags:
      application: bcnc-price-service
EOF

  # Mover el archivo temporal a la ubicación final
  mv "$TEMP_CONFIG" "$APP_CONFIG"
  echo -e "${GREEN}✓ Configuración de métricas añadida a application.yml${NC}"
fi

# Compilar el proyecto con el perfil de observabilidad activado
echo -e "${YELLOW}Compilando el proyecto con el perfil de observabilidad activado...${NC}"

# Compilar con el perfil activado
mvn clean package -Pobservability

echo -e "${GREEN}=== Configuración de observabilidad completada correctamente ===${NC}"
echo ""
echo -e "${YELLOW}Instrucciones para ejecutar el proyecto con observabilidad:${NC}"
echo "1. Ejecuta la aplicación con: java -jar boot/target/boot-*.jar --spring.profiles.active=observability"
echo "2. Monitorea los logs en: ./logs/application.log"
echo "3. Accede a las métricas en: http://localhost:8080/actuator/metrics"
echo "   - Para ver métricas específicas: http://localhost:8080/actuator/metrics/{nombre-métrica}"
echo "   - Ejemplos: jvm.memory.used, http.server.requests, system.cpu.usage"
echo ""
echo -e "${YELLOW}Para utilizar métricas en tu código:${NC}"
echo "1. Inyecta MeterRegistry en tus servicios o controladores:"
echo ""
echo "   @Autowired"
echo "   private MeterRegistry meterRegistry;"
echo ""
echo "2. Registra contadores, temporizadores o medidores:"
echo ""
echo "   // Para contar eventos"
echo "   Counter counter = meterRegistry.counter(\"api.price.requests\", \"brand\", brandId.toString());"
echo "   counter.increment();"
echo ""
echo "   // Para medir tiempos"
echo "   Timer timer = meterRegistry.timer(\"api.price.response.time\");"
echo "   return timer.record(() -> priceService.getPrice(dateTime, productId, brandId));"
echo ""
echo -e "${GREEN}¡Observabilidad configurada y lista para usar en entorno local!${NC}"
