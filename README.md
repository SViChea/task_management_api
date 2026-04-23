# 📋 Task Management API

> A production-ready REST API built with **Java Spring Boot** — designed to demonstrate real-world backend engineering practices including authentication, security, and containerization.

![Java](https://img.shields.io/badge/Java-21-orange?style=flat-square&logo=openjdk)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.x-brightgreen?style=flat-square&logo=springboot)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-16-blue?style=flat-square&logo=postgresql)
![Docker](https://img.shields.io/badge/Docker-ready-2496ED?style=flat-square&logo=docker)
![JWT](https://img.shields.io/badge/JWT-secured-black?style=flat-square&logo=jsonwebtokens)

---

## 👋 About This Project

This is a **portfolio project** built to showcase backend development skills. It implements a full authentication and security stack on top of a task management domain — the kind of infrastructure you'd find in a real production API.

> 💡 **For recruiters & developers:** You can run the full API locally in under 2 minutes using Docker. A demo account is pre-loaded for you to try all endpoints without registering.

---

## ✨ Features

### 🔐 Authentication & Security
- **JWT Authentication** — stateless token-based auth with role-based access control
- **Email OTP Verification** — 6-digit OTP sent via Gmail SMTP on registration
- **Spring Security 6** — filter chain, `DaoAuthenticationProvider`, stateless sessions

### 📋 Task Management
- Full CRUD for tasks
- Role-based access (`ROLE_USER`, `ROLE_ADMIN`)

### 🛠️ Developer Experience
- **Swagger UI** — fully documented and interactive API docs
- **Docker + Docker Compose** — one command to run everything
- **Profile-based config** — separate `dev` and `prod` environments
- **CORS configured** — ready for frontend integration

---

## 🧰 Tech Stack

| Layer | Technology |
|---|---|
| Language | Java 21 |
| Framework | Spring Boot 3 |
| Security | Spring Security 6, JWT (jjwt 0.12.6) |
| 2FA | dev.samstevens.totp |
| Database | PostgreSQL 16 |
| ORM | Spring Data JPA / Hibernate |
| Email | Spring Mail + Microsoft SMTP |
| Docs | SpringDoc OpenAPI (Swagger UI) |
| Build | Gradle |
| Container | Docker, Docker Compose |

---

## 🚀 Running the Project

### Option 1 — Docker Hub (Fastest) ⚡

No need to clone or build. Just pull and run:

**1. Create a `docker-compose.yml`:**

```yaml
services:
  task-management-api:
    build: .
    image: svichea/task-management-api:v1
    container_name: task-management-api
    env_file:
      - path: prod.env
    ports:
      - "8081:8081"
    depends_on:
      - postgres

  postgres:
    image: postgres:17.6
    container_name: task_db
    env_file:
      - path: prod.env
    ports:
      - "5432:5432"
```

**2. Create a `prod.env` file:**

```properties
JWT_SECRET=8df3a0da3596b08320349e20cefa5c7b66d16ccc61c504d21ca15c0b5ed965e3
MAIL_USERNAME=vichea@vcstate.onmicrosoft.com
MAIL_PASSWORD=tfshfzqmzndbwfpr

POSTGRES_USER=postgres
POSTGRES_PASSWORD=qwer
POSTGRES_DB=task_db
POSTGRES_SERVER=postgres
```

**3. Run:**

```bash
docker compose up -d
```

**4. Open Swagger UI:**

```
http://localhost:8081/swagger-ui.html
```

---

### Option 2 — Build From Source

**Prerequisites:** Java 21, Docker, Git

***1. Clone:***
```bash
git clone https://github.com/SViChea/task_management_api.git
cd task_management_api
```

***2. Create application-prod.properties:***
```properties
JWT_SECRET=8df3a0da3596b08320349e20cefa5c7b66d16ccc61c504d21ca15c0b5ed965e3
MAIL_USERNAME=vichea@vcstate.onmicrosoft.com
MAIL_PASSWORD=tfshfzqmzndbwfpr

POSTGRES_USER=postgres
POSTGRES_PASSWORD=qwer
POSTGRES_DB=task_db
POSTGRES_SERVER=postgres
```

***3. Build and run***
```bash
docker compose up --build -d
```

***4. Open Swagger UI***
open http://localhost:8081/swagger-ui.html

---


## 📖 API Documentation

Interactive Swagger UI is available after running the project:

```
http://localhost:8081/swagger-ui.html
```

### 🧪 Demo Account

A demo account is pre-loaded so you can try protected endpoints immediately:

| Field | Value |
|---|---|
| Username | `admin` |
| Password | `1234` |

**How to authenticate in Swagger:**
1. Call `POST /api/v1/login` with the demo credentials
2. Copy the `token` from the response
3. Click **Authorize 🔒** at the top of Swagger UI
4. Paste the token — no `Bearer` prefix needed
5. All protected endpoints are now unlocked ✅

### 🔑 Auth Endpoints
 
| Method | Endpoint | Description | Auth |
|---|---|---|---|
| `POST` | `/api/v1/auth/signup` | Register new account | Public |
| `POST` | `/api/v1/auth/login` | Login → get JWT | Public |
| `POST` | `/api/v1/auth/verify-otp` | Verify email OTP to activate account | Public |
| `POST` | `/api/v1/auth/resend-otp?email=` | Resend OTP to email | Public |
 
**Signup Request:**
```json
{
  "name": "John Doe",
  "username": "johndoe",
  "email": "john@gmail.com",
  "password": "password123"
}
```
 
**Login Request:**
```json
{
  "username": "johndoe",
  "password": "password123"
}
```
 
**Verify OTP Request:**
```json
{
  "email": "john@gmail.com",
  "otp": "482917"
}
```
 
---
 
### 📋 Task Endpoints
 
| Method | Endpoint | Description | Auth |
|---|---|---|---|
| `GET` | `/api/v1/tasks?id=` | Get task by ID | 🔒 JWT |
| `POST` | `/api/v1/tasks` | Create new task | 🔒 JWT |
| `PATCH` | `/api/v1/tasks` | Update task | 🔒 JWT |
| `DELETE` | `/api/v1/tasks?id=` | Delete task | 🔒 JWT |
| `GET` | `/api/v1/tasks/projects?projectId=` | Get all tasks by project | 🔒 JWT |
 
**Create Task Request:**
```json
{
  "title": "Implement login feature",
  "description": "Add JWT-based login endpoint",
  "dueDate": "2025-12-31T00:00:00",
  "progressId": 1,
  "priorityId": 2,
  "projectId": 1
}
```
 
**Update Task Request:**
```json
{
  "id": 1,
  "title": "Updated title",
  "description": "Updated description",
  "dueDate": "2025-12-31T00:00:00",
  "progressId": 2,
  "priorityId": 1
}
```
 
---
 
### 📁 Project Endpoints
 
| Method | Endpoint | Description | Auth |
|---|---|---|---|
| `GET` | `/api/v1/projects?userId=` | Get all projects by user | 🔒 JWT |
| `POST` | `/api/v1/projects` | Create new project | 🔒 JWT |
| `PATCH` | `/api/v1/projects?projectId=` | Update project | 🔒 JWT |
| `DELETE` | `/api/v1/projects?userId=&projectId=` | Delete project | 🔒 JWT |
 
**Create Project Request:**
```json
{
  "userId": 1,
  "title": "My Project",
  "description": "Project description"
}
```
 
---
 
### 📦 Standard Response Format
 
Every endpoint returns the same consistent structure:
 
```json
{
  "status": 200,
  "timestamp": "2025-01-01T00:00:00",
  "message": "Success",
  "data": {}
}
```
 
---
 
## 🔒 Authentication Flow
 
```
POST /api/v1/auth/signup
        ↓
OTP sent to email
        ↓
POST /api/v1/auth/verify-otp
        ↓
Account activated
        ↓
POST /api/v1/auth/login → JWT token
        ↓
Authorization: Bearer <token>
        ↓
Access protected endpoints ✅
```
 
---

---

## 📁 Project Structure

```
src/main/java/
├── base/            # Base Response for API Response
├── config/          # Swagger
├── controller/      # REST controllers
├── dto/             # Request / Response objects
├── exception/       # Handle exception with @RestControllerAdvice
├── init/            # Initial pre data
├── mapper/          # Mapping data from dto->dao, dao->dto
├── model/           # JPA entities
├── repository/      # JPA repositories
├── security/        # Security config, jwt, cors origin
└── service/         # Business logic
```

---

## 👤 Author

**SViChea**
- GitHub: [@SViChea](https://github.com/SViChea)
- Docker Hub: [svichea/task-management-api](https://hub.docker.com/r/svichea/task-management-api)

---

> ⭐ If you found this project useful, consider giving it a star!
