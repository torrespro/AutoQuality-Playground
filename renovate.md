# Renovate 

[Slides: La automatización como protagonista en la calidad del código](https://docs.google.com/presentation/d/1_b0LnPXTKBgKtzg5HX3rdSgU85CRvnMGI-c8R5bkqF0/edit?usp=sharing)

## Description

- [Renovate](https://docs.renovatebot.com/): Mantiene las dependencias actualizadas automáticamente.


En este ejemplo, utilizaré la variante “GitHub Action” de “Running Renovate”. Obviamente, la forma en que decidas ejecutar Renovate depende completamente de ti y debe ser decidida por los requisitos de tu equipo. 
En este caso, no tener dependencia externa (como en la versión alojada) y tener el control completo de la solución fueron los factores decisivos para nosotros.

Esta configuración te dará:

- Una configuración básica de [_Renovate_](https://github.com/torrespro/renovate-config/blob/main/default.json) que se utilizará como un conjunto de configuraciones compartibles que no abrumará a tus equipos de desarrollo, pero también hará que los equipos se beneficien de las mejoras de cada uno.
- Un Worfklow de [GitHub Actions](https://github.com/torrespro/renovate-config/blob/main/.github/workflows/renovate.yml) completamente controlable y reutilizable, reduciendo la cantidad de solicitudes de extracción por repositorio que utiliza el flujo de trabajo y teniendo el control para ejecutar Renovate de forma ad-hoc o programada. 


## Links

- Centralised config and Github Workflow: https://github.com/torrespro/renovate-config 
