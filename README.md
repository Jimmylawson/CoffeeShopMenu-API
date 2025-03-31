# Coffee Menu API ☕
![status](https://img.shields.io/badge/status-in--progress-yellow)

A RESTful API to manage coffee shop menu items...

A simple RESTful API for managing coffee shop menu items.

## Features
- Add, update, delete, and retrieve menu items
- Input validation with Spring Boot
- Uses MySQL and Spring Data JPA

## Endpoints

### `POST /menu-items`
Create a new menu item

### `GET /menu-items`
Retrieve all menu items

### `GET /menu-items/{id}`
Get a single menu item by ID

### `PUT /menu-items/{id}`
Update an existing item

### `DELETE /menu-items/{id}`
Delete an item by ID

## Sample JSON
```json
{
  "name": "Vanilla Latte",
  "description": "A rich espresso with steamed milk and vanilla syrup.",
  "price": 4.50,
  "category": "LATTE",
  "available": true
}

