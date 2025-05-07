# Coffee Menu API ☕

![status](https://img.shields.io/badge/status-in--progress-yellow)

A simple, production-ready RESTFUL  API for managing coffee shop menu items — built with Spring Boot, MySQL, JWT-based security, and documented using Swagger OpenAPI.

---

## 🚀 Features

- **CRUD Operations:**  
  Add, update, delete, and retrieve menu items.
- **Input Validation:**  
  Uses Spring Boot annotations for safe and reliable data handling.
- **Persistence:**  
  Powered by MySQL with Spring Data JPA.
- **Security:**  
  Implements JWT-based authentication along with role-based access control.
- **API Documentation:**  
  Interactive API docs generated via Swagger UI.

---

## 📘 API Documentation (Swagger UI)

To explore and test the API, run the application and visit:  
[http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

---

## 🔒 Security Overview

- **JWT Authentication:**  
  After a successful login, users receive a JWT token that must be included in the `Authorization` header (using the "Bearer " prefix) on protected endpoints.
- **Role-Based Access Control:**
    - **Public Endpoints:** GET requests for menu items and certain user endpoints (e.g., login, registration) are publicly accessible.
    - **Protected Endpoints:** POST, PUT, PATCH, and DELETE requests (for creating, updating, or deleting menu items, and admin user registration) require a valid JWT and the **ADMIN** role.
- **User Management:**  
  There are distinct endpoints for user registration (`/api/v1/user`), admin registration (`/api/v1/user-admin`), and login (`/api/v1/login`).

---

## 🛠️ Endpoints

### Menu Item Endpoints

| Method  | Endpoint                     | Description                     | Security Requirement         |
|---------|------------------------------|---------------------------------|------------------------------|
| POST    | `/api/v1/menu-items`         | Create a new menu item          | **ADMIN** (JWT required)     |
| GET     | `/api/v1/menu-items`         | Retrieve all menu items         | Public                       |
| GET     | `/api/v1/menu-items/{id}`    | Retrieve a menu item by ID      | Public                       |
| PUT     | `/api/v1/menu-items/{id}`    | Update an existing menu item    | **ADMIN** (JWT required)     |
| PATCH   | `/api/v1/menu-items/{id}`    | Partially update a menu item    | **ADMIN** (JWT required)     |
| DELETE  | `/api/v1/menu-items/{id}`    | Delete a menu item by ID        | **ADMIN** (JWT required)     |

### User & Authentication Endpoints

| Method  | Endpoint               | Description                                  | Security Requirement |
|---------|------------------------|----------------------------------------------|----------------------|
| POST    | `/api/v1/user`         | Register a new user                          | Public               |
| POST    | `/api/v1/user-admin`   | Register a new admin user                    | Restricted (ADMIN)   |
| POST    | `/api/v1/login`        | Authenticate a user and receive a JWT token  | Public               |

---

## 📦 Sample Request Body for Menu Item

```json
{
  "name": "Vanilla Latte",
  "description": "A rich espresso with steamed milk and vanilla syrup.",
  "price": 4.50,
  "category": "LATTE",
  "available": true
}

## 🐳 Running with Docker

### 📄 Prerequisites
- [Docker](https://www.docker.com/products/docker-desktop) installed on your machine
- Project built with:
  ```bash
  ./mvnw clean package

