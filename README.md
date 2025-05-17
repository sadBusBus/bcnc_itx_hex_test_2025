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
