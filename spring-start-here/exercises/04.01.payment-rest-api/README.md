# Payment REST API with Spring Boot

This project implements a simple REST API for managing payments using Spring Boot. It serves as an educational
resource for learning the basics of Spring web development, Spring MVC, REST API design, Spring Boot, and
Spring bean scopes and lifecycle.

## Project Structure

The project structure is organized as follows:

```text
src
└── main
    ├── java
    │   └── com
    │       └── payment
    │           └── rest_api
    │               ├── controllers
    │               │   ├── advice
    │               │   │   └── ExceptionControllerAdvice.java
    │               │   └── PaymentController.java
    │               ├── exceptions
    │               │   └── NotEnoughMoneyException.java
    │               ├── Main.java
    │               ├── models
    │               │   ├── ErrorDetails.java
    │               │   └── Payment.java
    │               ├── repositories
    │               │   ├── PaymentRepositoryImpl.java
    │               │   └── PaymentRepository.java
    │               └── services
    │                   ├── PaymentServiceImpl.java
    │                   └── PaymentService.java
    └── resources
        └── application.properties
```

### Controller Layer

- **controllers:** Contains controller classes responsible for handling HTTP requests.
  - `PaymentController.java`: Manages REST endpoints for payment operations.
  - `ExceptionControllerAdvice.java`: Provides centralized exception handling and error responses.

### Service Layer

- **services:** Implements business logic for handling payment operations.
  - `PaymentService.java`: Defines the contract for payment-related operations.
  - `PaymentServiceImpl.java`: Implements methods defined in the `PaymentService` interface.

### Repository Layer

- **repositories:** Provides interfaces for interacting with data storage.
  - `PaymentRepository.java`: Defines methods for managing payments.
  - `PaymentRepositoryImpl.java`: Implements methods defined in the `PaymentRepository` interface.

### Model Layer

- **models:** Contains POJO classes representing data models.
  - `Payment.java`: Represents the payment entity.
  - `ErrorDetails.java`: Represents error details for exception handling.

### Resources

- **application.properties:** Configuration file for Spring Boot properties.

### Exception Controller Advice

- **advice:** Contains the `ExceptionControllerAdvice.java` class.
  - This advice class intercepts exceptions thrown by controllers and generates appropriate error responses.

## Exposed APIs

The following APIs are exposed by this project:

- **Create a new payment:**
  - POST `/api/payments`
- **Retrieve a payment by its ID:**
  - GET `/api/payments/{id}`
- **Retrieve all payments:**
  - GET `/api/payments`
- **Process a payment by its ID:**
  - PUT `/api/payments/{id}/process`
  
## How to Test

### Using cURL Commands

#### Create a new payment

```bash
curl -X POST -H "Content-Type: application/json" -d '{"amount": 100.0, "currency": "USD", "status":
"Pending"}' http://localhost:8080/api/payments
```

#### Retrieve a payment by its ID

```bash
curl -X GET http://localhost:8080/api/payments/{id}
```

#### Retrieve all payments

```bash
curl -X GET http://localhost:8080/api/payments
```

#### Process a payment by its ID

```bash
curl -X PUT http://localhost:8080/api/payments/{id}/process
```

Replace `{id}` with the actual ID of the payment you want to retrieve or process.

## License

This project is licensed under the MIT License - see the [LICENSE](./LICENSE) file for details.
