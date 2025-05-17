# bcnc_itx_hex_test_2025
## Estructura General

Este proyecto está organizado siguiendo los principios de la **Arquitectura Hexagonal** (también conocida como Ports and Adapters), implementada con una estrategia de múltiples módulos en Java. La estructura principal se divide en:

### Módulos Principales

1. **Boot**: El módulo de arranque que inicializa toda la aplicación y configura los componentes.

2. **Service**: Contiene el dominio de la aplicación y la lógica de negocio, siguiendo los principios de Domain-Driven Design (DDD).

3. **Driving** (Adaptadores Primarios): Gestiona las entradas al sistema, como las API REST o consumidores de Kafka. Estos adaptadores inician las interacciones con la aplicación.

4. **Driven** (Adaptadores Secundarios): Contiene los módulos que interactúan con sistemas externos como bases de datos, servicios de correo electrónico o llamadas a APIs externas.

### Filosofía de la Arquitectura

La arquitectura hexagonal aplicada en este proyecto busca:

- **Separación clara de responsabilidades**: Cada módulo tiene un propósito específico.
- **Independencia del dominio**: La lógica de negocio (en el módulo `service`) está aislada de los detalles de implementación.
- **Modularidad**: Cada funcionalidad externa está encapsulada en su propio módulo dentro de `driven`.

## Flujo de Operación

1. Las solicitudes entran al sistema a través de los adaptadores primarios (`driving`).
2. Estas solicitudes se pasan al dominio (`service`), donde se aplica la lógica de negocio.
3. Si es necesario, el dominio utiliza puertos que se implementan en los adaptadores secundarios (`driven`) para interactuar con sistemas externos.

## Gestión del Proyecto

- El proyecto utiliza Maven para la gestión de dependencias y configuración de módulos.
- Tiene un archivo `pom.xml` principal que define las dependencias globales y los módulos del proyecto.
- Incluye un flujo de CI/CD básico configurado mediante GitHub Actions.

Esta arquitectura facilita:
- El desarrollo de pruebas unitarias al aislar el dominio
- La sustitución de tecnologías externas (por ejemplo, cambiar una base de datos por otra)
- La escalabilidad al poder añadir nuevos módulos `driven` para nuevas integraciones

Cada módulo `driven` corresponde a una única acción o integración externa, lo que mejora la mantenibilidad del código y permite una evolución más controlada del sistema.

```
┌─────────────────────────────────────────────────────────────────────┐
│                                                                     │
│  ┌───────────────┐      ┌───────────────┐      ┌───────────────┐    │
│  │   DRIVING     │      │    SERVICE    │      │    DRIVEN     │    │
│  │  (Adaptadores │      │   (Dominio)   │      │  (Adaptadores │    │
│  │   Primarios)  │      │               │      │  Secundarios) │    │
│  │               │      │               │      │               │    │
│  │  Controllers  │      │   Lógica de   │      │  Repository   │    │
│  │   REST API    │───→  │    Negocio    │───→  │  Implementors │    │
│  │ Kafka Consume │      │    Domain     │      │  Email Sender │    │
│  │               │      │    Models     │      │   External    │    │
│  │               │ ←─── │               │ ←─── │     APIs      │    │
│  └───────────────┘      └───────────────┘      └───────────────┘    │
│                                                                     │
│                         BOOT MODULE                                 │
│                  (Inicialización y Configuración)                   │
└─────────────────────────────────────────────────────────────────────┘
```


## Arquitectura Hexagonal: Decisiones de Diseño

### 1. Núcleo del Dominio (Service)

El módulo `service` representa el corazón de la aplicación y contiene:

- **Modelos de dominio**: Entidades puras que representan los conceptos centrales del negocio
- **Puertos**: Interfaces que definen cómo el dominio interactúa con el exterior
    - **Puertos de entrada (driving ports)**: Definen los servicios que expone el dominio
    - **Puertos de salida (driven ports)**: Definen las interfaces que necesita el dominio

**Decisión clave**: Mantener el dominio completamente aislado de detalles de infraestructura, permitiendo que la lógica de negocio sea testeable y evolucione independientemente.

### 2. Adaptadores Primarios (Driving)

El módulo `driving` implementa los puntos de entrada a la aplicación:

- **Controladores REST**: Implementan las APIs y transforman las peticiones en llamadas al dominio
- **Consumidores de eventos**: Procesan mensajes de sistemas de mensajería (como Kafka)
- **Mappers**: Transforman DTOs (objetos de transferencia de datos) en entidades de dominio

**Decisión clave**: Separar los controladores por funcionalidad para mantener una clara separación de responsabilidades, como se ve en `PriceController`.

### 3. Adaptadores Secundarios (Driven)

El módulo `driven` implementa las interfaces definidas por los puertos de salida del dominio:

- **Repositorios**: Implementan el acceso a datos
- **Clientes de servicios externos**: Implementan la comunicación con otros sistemas
- **Servicios de infraestructura**: Implementan funcionalidades como envío de emails, notificaciones, etc.

**Decisión clave**: Diseñar cada adaptador como un módulo independiente para facilitar el reemplazo de tecnologías sin afectar el dominio.

### 4. Módulo de Arranque (Boot)

El módulo `boot` es responsable de:

- **Configuración**: Propiedades de la aplicación, conexiones a bases de datos, etc.
- **Inicialización**: Levanta los componentes necesarios en el orden correcto
- **Inyección de dependencias**: Conecta los adaptadores con el dominio a través de los puertos

**Decisión clave**: Centralizar toda la configuración en este módulo para mantener el resto de la aplicación libre de detalles de inicialización.

## Flujo de Datos y Operaciones

1. **Request → Adaptador Primario**: Las solicitudes llegan a través de controladores REST (como `PriceController`)
2. **Adaptador Primario → Dominio**: Los adaptadores primarios invocan los puertos de entrada del dominio
3. **Dominio → Adaptador Secundario**: El dominio utiliza los puertos de salida, implementados por adaptadores secundarios
4. **Dominio → Adaptador Primario**: El resultado regresa al adaptador primario
5. **Adaptador Primario → Response**: El adaptador primario transforma y entrega la respuesta al cliente
