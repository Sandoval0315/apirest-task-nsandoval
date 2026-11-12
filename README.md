# API de tareas

API REST para administrar tareas. Permite crear, consultar, actualizar y eliminar tareas mediante JSON.

## Tecnología

- Java 17
- Spring Boot: se usó porque facilita crear APIs REST, validar datos y conectar una base de datos.
- Spring Data JPA con H2: H2 es una base de datos liviana que corre en memoria. No se necesita instalar ni configurar un servidor de base de datos.

## Ejecutar localmente

Requisito: JDK 17 o superior.

```bash
./mvnw spring-boot:run
```

La API queda disponible en `http://localhost:8080`. La información se guarda mientras la aplicación está encendida; al detenerla, las tareas se eliminan ya que se guarda en memoria.

Para ejecutar las pruebas automatizadas:

```bash
./mvnw test
```

## Ejecutar con Docker

```bash
docker build -t task-management-api .
docker run --rm -p 8080:8080 task-management-api
```

## Integración continua

El flujo de GitHub Actions revisa que el proyecto compile correctamente cuando se suben cambios.

## Endpoints

| Método | Ruta | Descripción |
| --- | --- | --- |
| GET | `/` | Confirma que la API está corriendo. |
| GET | `/api/tasks` | Lista todas las tareas. |
| GET | `/api/tasks/{id}` | Obtiene una tarea por identificador. |
| POST | `/api/tasks` | Crea una tarea. |
| PUT | `/api/tasks/{id}` | Actualiza una tarea existente. |
| DELETE | `/api/tasks/{id}` | Elimina una tarea. |

### Crear una tarea

```bash
curl -X POST http://localhost:8080/api/tasks \
  -H 'Content-Type: application/json' \
  -d '{"title":"Preparar documentación","description":"Documentar endpoints","isCompleted":false}'
```

El campo `title` es obligatorio y no puede estar vacío. Si `isCompleted` no se envía, se guarda como `false`.

## Respuestas de error

- `400 Bad Request`: cuerpo JSON inválido o `title` vacío.
- `404 Not Found`: no existe una tarea para el id solicitado.

Ejemplo de error de validación:

```json
{
  "estado": 400,
  "mensaje": "el titulo es obligatorio"
}
```
