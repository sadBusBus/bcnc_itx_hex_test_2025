# bcnc_itx_hex_test_2025

Ejemplo sencillo de arquitectura hexagonal multimodule.

Tenemos un pom.xml padre , que tiene todas las dependencias globales que necesite el proyecto ,
ademas de los modulos.

El boot , inicializa todo el proyecto.

Basicamente separamos el adapter , del domain y del repository ...

En nuestro caso , nuestras llamadas API llegaran a driving , o consumidores de kafka en caso sean necesarios ...

La logica la ostentara el service , haciendo DDD , basicamente toda la logica sera hospedada en el service...

Driven tendra el resto , tendremos un modulo por una single accion , ejemplo base de datos un modulo , que queremos un modulo para emails , pues un modulo para emails , o tal vez un modulo para hacer llamadas rest to rest ...

En cuanto CI/CD tiene solo un workflow que tira un par de comandos de maven , pero si quiero puedo montar un vps o hospedar en los diferentes clouds un sonar o lo que se me antoje ...
