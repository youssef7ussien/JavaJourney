# Simple Purchase Example

The Purchase Application is a simple Java Spring Boot backend project designed to manage purchases. It utilizes the H2 database and JdbcTemplate for data storage and retrieval. The project follows the MVC pattern without using JPA or security features. The purpose of this project is to demonstrate basic CRUD operations for purchases and showcase exception handling techniques.

## Project Structure

The project structure is organized as follows:
```
src
└── main
    ├── java
    │   └── com
    │       └── h2
    │           └── example
    │               ├── controllers
    │               │   ├── advices
    │               │   │   └── GlobalExceptionHandler.java
    │               │   └── PurchaseController.java
    │               ├── exceptions
    │               │   ├── ControllerException.java
    │               │   └── RepositoryException.java
    │               ├── Main.java
    │               ├── models
    │               │   └── Purchase.java
    │               ├── repositories
    │               │   ├── PurchaseRepositoryImpl.java
    │               │   └── PurchaseRepository.java
    │               └── services
    │                   ├── PurchaseServiceImpl.java
    │                   └── PurchaseService.java
    └── resources
        ├── application.properties
        ├── schema.sql
```

## Exposed APIs

The Purchase Application exposes the following APIs:

1. **Save a Purchase**

   - **Endpoint**: `POST /purchases`
   - **Description**: Saves a new purchase.
   - **Request Body**: JSON object with `Product` (String), `price` (Double) and `quantity` (Int).
   - **Response**: HTTP status code (201 Created for success).

2. **Delete a Purchase by ID**

   - **Endpoint**: `DELETE /purchases/{id}`
   - **Description**: Deletes a purchase by ID.
   - **Response**: HTTP status code (200 OK for success).

3. **Find a Purchase by ID**

   - **Endpoint**: `GET /purchases/{id}`
   - **Description**: Retrieves a purchase by ID.
   - **Response**: JSON object representing the purchase, or an error message if not found.

4. **Retrieve All Purchases**

   - **Endpoint**: `GET /purchases`
   - **Description**: Retrieves all purchases.
   - **Response**: JSON array containing all purchases, or an empty array if none found.

## Testing with cURL

You can test the APIs using cURL commands. Refer to the documentation above for examples.

## How to Test

You can test the Purchase Application endpoints using cURL commands:

### Save a purchase

```bash
curl -X POST -H "Content-Type: application/json" -d '{"product": "Product", "price": 10.99, "quantity": 1}' http://localhost:8080/purchases
```

### Delete a purchase by ID

```bash
curl -X DELETE http://localhost:8080/purchases/{id}
```

### Find a purchase by ID

```bash
curl http://localhost:8080/purchases/{id}
```

### Retrieve all purchases

```bash
curl http://localhost:8080/purchases
```

> [!NOTE]
> Replace {id} with the actual ID of the purchase you want to interact with.

## License

This project is licensed under the MIT License - see the [LICENSE](./LICENSE) file for details.
