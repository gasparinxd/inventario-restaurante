# Inventario Restaurante

[![docker-compose](https://github.com/gasparinxd/inventario-restaurante/actions/workflows/docker-compose.yml/badge.svg)](https://github.com/gasparinxd/inventario-restaurante/actions/workflows/docker-compose.yml)

Aplicación para la gestión de inventario de un restaurante.

- **backend/**: API REST con Spring Boot 4 (Java 21) y PostgreSQL.
- **frontend/**: aplicación web en React (Vite), servida con Nginx en Docker.
- **docker-compose.yml**: levanta base de datos, backend y frontend a la vez.

## Requisitos

- Docker y Docker Compose

Para desarrollo local sin Docker: Java 21+ y Node.js 22+.

## Levantar con Docker

```bash
docker compose up --build
```

| Servicio   | URL                                    |
|------------|----------------------------------------|
| Frontend   | http://localhost:3000                  |
| Backend    | http://localhost:8080/api/productos    |
| Health     | http://localhost:8080/actuator/health  |
| PostgreSQL | localhost:5432                         |

Los valores de BD y puertos se leen del archivo `.env` (no versionado). Si no existe, se usan los valores por defecto: BD/usuario/contraseña `inventario`, puertos 5432, 8080 y 3000.

Para detener: `docker compose down` (añade `-v` para borrar los datos de la BD).

## GitHub Codespaces

1. En GitHub: **Code → Codespaces → Create codespace on main**.
2. Al arrancar, el Codespace ejecuta `docker compose up -d --build` automáticamente (tarda 1–3 minutos la primera vez).
3. Abre la pestaña **Ports** y entra al puerto **3000 (Frontend)**. Si aparece *HTTP 401*, cambia la visibilidad del puerto a **Public**.

Si un Codespace se creó antes de un cambio en `.devcontainer.json`, usa **Codespaces: Rebuild Container** desde la paleta de comandos.

## Integración continua

Cada push a `main` ejecuta el workflow `.github/workflows/docker-compose.yml`, que levanta los tres servicios con `docker compose` y comprueba el backend, la API y el frontend.

## Desarrollo local

```bash
# Base de datos
docker compose up -d db

# Backend (http://localhost:8080)
cd backend
./mvnw spring-boot:run

# Frontend (http://localhost:5173, redirige /api al backend)
cd frontend
npm install
npm run dev
```

## API

| Método | Ruta                   | Descripción         |
|--------|------------------------|---------------------|
| GET    | `/api/productos`       | Listar productos    |
| GET    | `/api/productos/{id}`  | Obtener un producto |
| POST   | `/api/productos`       | Crear producto      |
| DELETE | `/api/productos/{id}`  | Eliminar producto   |

Ejemplo:

```bash
curl -X POST http://localhost:8080/api/productos \
  -H "Content-Type: application/json" \
  -d '{"nombre":"Tomate","unidad":"kg","cantidad":12.5}'
```
