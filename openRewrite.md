# OpenRewrite

[Slides: La automatización como protagonista en la calidad del código](https://docs.google.com/presentation/d/1_b0LnPXTKBgKtzg5HX3rdSgU85CRvnMGI-c8R5bkqF0/edit?usp=sharing)

## Description

- [Open Rewrite](https://docs.openrewrite.org/): Realiza refactorizaciones automáticas en el código.
Un ejemplo de uso ha sido actualizar las versiones de Spring y Java en este proyecto ejecutando las recetas directamente:
- https://docs.openrewrite.org/recipes/java/spring/boot3/upgradespringboot_3_2
`  mvn -U org.openrewrite.maven:rewrite-maven-plugin:run -Drewrite.recipeArtifactCoordinates=org.openrewrite.recipe:rewrite-spring:RELEASE -Drewrite.activeRecipes=org.openrewrite.java.spring.boot3.UpgradeSpringBoot_3_2`
- https://docs.openrewrite.org/recipes/java/migrate/upgradetojava21
`  mvn -U org.openrewrite.maven:rewrite-maven-plugin:run -Drewrite.recipeArtifactCoordinates=org.openrewrite.recipe:rewrite-migrate-java:RELEASE -Drewrite.activeRecipes=org.openrewrite.java.migrate.UpgradeToJava21`

## Links

- https://github.com/moderneinc/rewrite-recipe-starter 
- Intellij Plugin: https://www.jetbrains.com/help/idea/openrewrite.html
