# Error Prone

## Description

- [Error Prone](https://github.com/google/error-prone/releases): Detecta (y corrige) errores comunes en Java durante la compilación.

- Modulo [errorprone-bugpatterns](quality-rules%2Ferrorprone-bugpatterns]) con un paquete de reglas customizadas lista para utilizar

Se incluyen 3 perfiles en este pom para usar error prone:
- _error-prone-warn_: Print all the finding as warnings
- _error-prone_: Normal execution to find the bugs specified in the configuration
- _patch_: Apply fixes automatically to the bugs found

## Build

    mvn clean install -f quality-rules/errorprone-bugpatterns/pom.xml

## Usage

    mvn clean compile -Perror-prone-warn

    mvn clean compile -Perror-prone

    mvn clean compile -Ppatch


## Customisation

[Write your own BugChecker](https://github.com/google/error-prone/wiki/Writing-a-check)


## Useful links

- Error Prone library from Picnic: https://error-prone.picnic.tech/
- Presentation in Spring I/O: https://www.youtube.com/watch?v=-47WD-3wKBs
