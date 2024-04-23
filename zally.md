# Zally

[Slides: La automatización como protagonista en la calidad del código](https://docs.google.com/presentation/d/1_b0LnPXTKBgKtzg5HX3rdSgU85CRvnMGI-c8R5bkqF0/edit?usp=sharing)

## Description
  
- [Zally](https://opensource.zalando.com/zally/): Automatiza verificaciones de API REST contra estándares OpenAPI.

  - Modulo [zally-openapi-lint-rules](quality-rules%2Fzally-openapi-lint-rules) con un paquete de reglas customizadas lista para utilizar
  - Se incluye 1 perfil en este pom para usar zally con el maven plugin https://github.com/ethlo/zally-maven-plugin

## Build

    mvn clean install -f quality-rules/zally-openapi-lint-rules/pom.xml

## Use

    mvn clean compile -Pzally

## Useful links

- Zally Server: https://github.com/zalando/zally/tree/main/server
- Zally UI: https://github.com/zalando/zally/blob/main/web-ui/README.md
- Zally CLI: https://github.com/zalando/zally/blob/main/cli/README.md
- Zalando API Guidelines: https://opensource.zalando.com/restful-api-guidelines/