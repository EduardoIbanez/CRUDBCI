<# Proyecto crudBCI

## 1. Introducción
El proyecto consiste en un CRUD API Rest para menejo de usuarios.

## 2. Herramientas

* Java 17
* SpringBoot 3.4.4
* H2 Database
* Gradle
* IntelliJ IDEA o Eclipse
* Git
* Swagger
* JUnit

## 3. Ejecución

Es necesario Abrir el IDE y ejecutarlo.

La url por defecto del proyecto es localhost:9090/api

### 3.1 URL Postman
* GET    -> localhost:9090/api/user
* GET    -> localhost:9090/api/allUsers
* POST   -> localhost:9090/api/create
* PATCH  -> localhost:9090/api/updateActive
* DELETE -> localhost:9090/api/updatePatch

La url para la creación de usuario es la siguiente: localhost:9090/create. 
Esta es la unica url sin necesida de token, ya que es la que permite generar el usuario y el token.

Para poder usar el resto de las url disponibles, es necesario agregar el token obtenido en postman o en el swagger,
una vez creado el usuario

Se puede acceder a swagger una vez ejecutado el proyecto en la siguiente
dirección: localhost:9090/doc/sawagger-ui-html

Para la revisión de la DB se accede a la siguiente url: localhost:9090/h2-console,
los datos de conexión, así como usuario y contraseña se encuentran en el archivo application.properties

El script de la base de datos  se encuentra ubicado en /src/main/resources

El diagrama de secuencia de la solución se encuentra ubicado en /src/main/resources

Los Test unitarios se realizaron con JUnit.

