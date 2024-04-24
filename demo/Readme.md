# AutoQuality-Playground 

[Slides: La automatización como protagonista en la calidad del código](https://docs.google.com/presentation/d/1_b0LnPXTKBgKtzg5HX3rdSgU85CRvnMGI-c8R5bkqF0/edit?usp=sharing)

## Description

Un repositorio donde se exploran técnicas de automatización para garantizar la calidad del código.

Contiene una aplicación ejemplo en Java/Spring Boot 
La aplicación también incluye una API REST para gestionar libros, comentarios de los libros y usuarios
La aplicación consta de dos Controllers, uno para la API Rest de Libros y Comentarios y otro que maneja la API de usuarios, dos Servicios que maneja la lógica de negocio de cada controller y el acceso a los datos, y luego las clases de Modelo.
La persistencia se hace en base de datos MySQL, el esquema es autogenerado basado en las @Entity definidas en código.

![db-schema](./db-schema.png)

## Technologies used

- Java 21
- Spring Boot 3.2
- OpenAPI 3.0.0
- JUnit 5
- SpringDoc OpenAPI
- MySQL 5.7/8.0.22
- [OpenAPI Generator for HTML](https://openapi-generator.tech/docs/generators/html2/)
- [Java Faker](https://github.com/DiUS/java-faker)
- [Error Handling Spring Boot](https://github.com/wimdeblauwe/blog-example-code/tree/master/error-handling-lib-example)
- [Datasource proxy](https://github.com/gavlyukovskiy/spring-boot-data-source-decorator)
- [Schema Spy](https://schemaspy.org/)

## Requirements

- Java 21
- Maven 3.8 or higher

## Documentation

- [OpenAPI spec](./api-docs/api-docs.yaml)
- [OpenAPI html](./api-docs/api-docs.html)
- [Database Documentation html](./db-docs/books/index.html)

## Build

    mvn clean verify

Genera el executable jar dentro de /target, la OpenAPI spec y el OpenAPI html dentro de la carpeta /api-docs

## Setup 

Necesitamos una Base de Datos MySQL funcionando:

    > docker run --rm -e MYSQL_ROOT_PASSWORD=backbase -e  MYSQL_DATABASE=books -p 3306:3306 -d mysql:8.0.22

Spring boot application:

    > mvn spring:boot run

or

    > java -jar ./target/holamundo-spring-0.0.1-SNAPSHOT.jar


Hay multiples maneras de ejecutar una aplicación de Spring Boot, la documentación explica varias de ellas:

https://docs.spring.io/spring-boot/docs/current/reference/html/using-boot-running-your-application.html 

## How to test

Durante el arranque de la aplicación se inserta [test-data](./src/main/resources/import.sql), 1 libro (con Ids 1) con 1 comentario (con Ids 1) y un Usuario con ID "juan". 
Se pueden probar los endpoints usando la [Postman](https://www.postman.com/) collection incluida:

- [Postman Collection](./MasterCloudApps-Books.postman_collection.json)
