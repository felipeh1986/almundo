Almundo Call Center
===================


Almundo call center es una aplicación prototipo que simula la atención de llamadas a través de un call center estableciendo algunos criterios mínimos como es el control concurrente de las llamadas y la disponibilidad de empleados que las atienden.

----------


Instalación de la aplicación
-------------

Una vez descargado el código fuente se debe ejecutar los siguientes comandos para su instalación y despliegue:

- Compilar la aplicación una vez estemos ubicados en la raiz del proyecto (donde se encuentra el archivo POM)
> mvn clean install

- Iniciar la micro aplicación a través del siguiente comando
> mvn spring-boot:run

También se puede iniciar como una aplicación java corriendo la clase principal llamada "CallCenterApplication.java"

> **Nota:**

>Los comandos de este ejemplo son realizado en Windows

Las URL de lo servicios son las siguientes:

Servicio para lanzar una llamada:

>- http:localhost:9000/almundo/callcenter/{phoneNumber}

Servicio para crear un empleado:

>- http:localhost:9000//almundo/callcenter/createEmployee

Servicio para obtener la lista de empleados persistidos en base de datos:

>- http:localhost:9000//almundo/callcenter/getEmployees

Documentación
-------------

El diseño de la aplicación se encuentra en el archivo llamado "Almundo Call Center.eap" el cual es un proyecto realizado en Enterprise Architech.
Si no dispone del programa mencionado anteriormente puede observar el diseño en el archivo "Diseño Call Center" ubicado en la directorio "documentation" de este repositorio.

Artefactos de prueba
-------------
Como artefacto de prueba se ha puesto a su disposición un proyecto elaborado con SOAP-UI llamado "AlmundoCallCenter-soapui-project.xml" que permite consumir los servicios Rest expuestos con sus respectivos datos de prueba.

Tecnologías implementadas
-------------
La aplicación esta basada en microservicios realizados con SpringBoot en la versión 1.5.3 con Java 8 y JPA (Java Persistence API) en la capa de acceso a datos.

> **Nota:**

> En esta aplicación se ha utilizado una base de datos embebida H2 y la cual es cargada con algunos empleados al momento de iniciar la aplicación.