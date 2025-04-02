# Coffee Menu API ☕
![status](https://img.shields.io/badge/status-in--progress-yellow)

A simple, production-ready RESTful API for managing coffee shop menu items — built with Spring Boot, MySQL, and documented using Swagger OpenAPI.

---

## 🚀 Features

- ✅ Add, update, delete, and retrieve menu items
- ✅ Input validation using Spring Boot annotations
- ✅ Uses MySQL with Spring Data JPA for persistence
- ✅ Auto-generated and interactive API docs via Swagger UI
- 🔒 Security-ready architecture (JWT support coming soon)

---

## 📘 API Documentation (Swagger UI)

To explore and test the API, run the application and visit:

[http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)
You can test each endpoint interactively and view schema definitions.

---

## 🛠️ Endpoints

| Method | Endpoint                 | Description                |
|--------|--------------------------|----------------------------|
| POST   | `/api/v1/menu-items`     | Create a new menu item     |
| GET    | `/api/v1/menu-items`     | Retrieve all menu items    |
| GET    | `/api/v1/menu-items/{id}`| Get a menu item by ID      |
| PUT    | `/api/v1/menu-items/{id}`| Update a menu item         |
| DELETE | `/api/v1/menu-items/{id}`| Delete a menu item by ID   |

---

## 📦 Sample Request Body

```json
{
  "name": "Vanilla Latte",
  "description": "A rich espresso with steamed milk and vanilla syrup.",
  "price": 4.50,
  "category": "LATTE",
  "available": true
}

## 👨‍💻 Author
**Jimmy Dev**  
📧 [jimoseipeace2bb@gmail.com](mailto:jimoseipeace2bb@gmail.com)



