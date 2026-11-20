# Open Treatment Platform (PC2 - Desarrollo de Aplicaciones Open Source)

RESTful API del caso **Open Treatment**: registra enfermedades (`diseases`) de un diagnóstico.
Construido con Domain-Driven Design, CQRS y arquitectura por capas, siguiendo el proyecto *learning-center-platform* del profesor.

- Java 27, Spring Boot 4.1.1, Spring Data JPA, Maven
- PostgreSQL (esquema `treatments`)
- springdoc-openapi 3.1.1 (Swagger UI), Lombok

## Cómo ejecutar

1. Tener PostgreSQL en `localhost:5432`. Por defecto la aplicación usa la base `postgres`, el usuario `postgres` y la clave `password`.
   Para cambiarlos, define las variables de entorno `DATABASE_URL`, `DATABASE_PORT`, `DATABASE_NAME`, `DATABASE_USER` y `DATABASE_PASSWORD`
   (en IntelliJ: *Run > Edit Configurations > Environment variables*).
2. El esquema `treatments` se crea solo al iniciar (`src/main/resources/schema.sql`) y la tabla `treatments.diseases` la crea Hibernate.
3. Ejecutar `TreatmentsPlatformApplication` o `mvn spring-boot:run`.
4. La API escucha en `http://localhost:8092`.
5. Swagger UI: `http://localhost:8092/swagger-ui.html`. Especificación OpenAPI: `http://localhost:8092/v3/api-docs`.

## Endpoint

`POST /api/v1/treatments/{diagnosisId}/diseases`

El `diagnosisId` va en la ruta, no en el body.

```json
{
  "diseaseName": "Influenza",
  "category": "RESPIRATORY",
  "causativeAgentId": 1,
  "commonSymptomsId": 2,
  "transmissionMethodId": 3,
  "preventionMethods": "Annual vaccination",
  "mortalityRatePercent": 0.05,
  "incidenceRatePer100k": 1200
}
```

Respuesta `201 Created`:

```json
{
  "id": 1,
  "diseaseName": "Influenza",
  "category": "RESPIRATORY",
  "causativeAgentId": 1,
  "commonSymptomsId": 2,
  "transmissionMethodId": 3,
  "preventionMethods": "Annual vaccination",
  "diagnosisId": 1,
  "mortalityRatePercent": 0.05,
  "incidenceRatePer100k": 1200
}
```

Categorías válidas (en BD se guarda el número): `INFECTIOUS`=1, `CARDIOVASCULAR`=2, `GASTROINTESTINAL`=3, `MUSCULOSKELETAL`=4,
`NEUROLOGICAL`=5, `RESPIRATORY`=6, `MENTAL`=7, `IMMUNE`=8.

## Reglas de negocio y errores

| Regla | Dónde se valida | Respuesta |
|---|---|---|
| No repetir `diseaseName` + `causativeAgentId` + `diagnosisId` | `DiseaseCommandServiceImpl` (y restricción única en BD) | 409 Conflict |
| `diseaseName` obligatorio, no vacío, máximo 80 | `CreateDiseaseCommand` | 400 Bad Request |
| `preventionMethods` obligatorio, no vacío, máximo 50 | `CreateDiseaseCommand` | 400 Bad Request |
| `mortalityRatePercent` obligatorio, entre 0.0 y 1.0 | `CreateDiseaseCommand` | 400 Bad Request |
| `category` debe coincidir con el nombre de una categoría | `Category.fromName` | 400 Bad Request |
| Ids (`causativeAgentId`, `commonSymptomsId`, `transmissionMethodId`, `diagnosisId`) no nulos y mayores que cero | Value objects | 400 Bad Request |
| JSON mal formado o `diagnosisId` no numérico | `GlobalExceptionHandler` | 400 Bad Request |

Los errores se devuelven como `ProblemDetail` (RFC 9457) y los mensajes se localizan con `Accept-Language` (`en` por defecto, `es`).

## Estructura

```
org.treatments.platform.u20211c201
├── shared        (Result, ApplicationError, naming strategy, OpenAPI, manejo de excepciones, auditoría)
└── diagnosis     (bounded context)
    ├── domain          (Disease, Category, value objects, command, query, repositorio)
    ├── application     (command service y query service)
    ├── infrastructure  (entidad JPA, converters, assembler, adaptador del repositorio)
    └── interfaces/rest (DiseasesController, resources, assemblers)
```

## Antes de entregar

- Reemplazar `Apellidos, Nombres` por tu nombre en los `@author` (en IntelliJ: *Edit > Find > Replace in Files*, `Ctrl+Shift+R`).
- Comprimir la carpeta del proyecto **sin** `target/` ni `.idea/` como `pc216692u20211c201prueba.zip`.

`requests.http` contiene pruebas manuales listas para el HTTP Client de IntelliJ.
