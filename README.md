# Productos Service

![CI](https://github.com/AlessandroRL/reyes-post2-u9/actions/workflows/ci.yml/badge.svg)

## Descripción

Microservicio de gestión de productos con API REST, pruebas unitarias e integración, y cobertura JaCoCo.

## Ejecutar las pruebas

```bash
mvn test # Solo pruebas unitarias
mvn verify # Pruebas + reporte JaCoCo
```

## Cobertura

![Captura JaCoCo](docs/jacoco-report.png)

## Pruebas locales

![Captura JaCoCo](docs/jacoco-report1.png)
![Captura JaCoCo](docs/jacoco-report2.png)
![Captura JaCoCo](docs/jacoco-report3.png)

## Evidencia

El detalle de las pruebas y la verificación del proyecto está documentado en [docs/VERIFICACION.md](docs/VERIFICACION.md).

## Tecnologías

- Spring Boot
- Spring Data JPA
- H2 en memoria para tests
- JUnit 5
- Mockito
- JaCoCo

## Estado del proyecto

- Las pruebas locales ejecutan en verde (13 pruebas en total)
- La cobertura de `ProductoServiceImpl` supera el 70% en líneas
- El reporte JaCoCo se genera en `target/site/jacoco/index.html`
- El workflow de GitHub Actions está configurado en `.github/workflows/ci.yml`