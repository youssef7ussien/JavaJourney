# Wallet Example

The Wallet Application is a Java Spring Boot backend project designed to manage Wallet transactions. It
utilizes the H2 database and Spring Data JDBC. The project follows the MVC pattern and includes unit tests for
comprehensive testing. The purpose of this project is to demonstrate basic CRUD operations with Spring Data,
unit testing, and showcase exception handling techniques.

## Technologies Used

- Java
- Spring Boot
- Spring Data JDBC
- H2 Database

## Project Structure

The project follows a standard Spring Boot project structure:

```text
src
├── main
│   ├── java
│   │   └── com
│   │       └── example
│   │           └── wallet
│   │               ├── config
│   │               │   └── ProjectConfig.java
│   │               │       # Configuration classes for Spring Boot application.
│   │               ├── controllers
│   │               │   ├── advice
│   │               │   │   └── GlobalExceptionHandle.java
│   │               │   │       # Handles global exceptions for controllers.
│   │               │   ├── TransactionController.java
│   │               │   │       # Manages endpoints related to transactions.
│   │               │   └── UserController.java
│   │               │       # Manages endpoints related to users.
│   │               ├── dto
│   │               │   ├── TransactionDTO.java
│   │               │   │       # Data transfer object for transactions.
│   │               │   ├── TransferDTO.java
│   │               │   │       # Data transfer object for transfer operations.
│   │               │   ├── UpdateBalanceDTO.java
│   │               │   │       # Data transfer object for updating user balances.
│   │               │   └── UserDTO.java
│   │               │       # Data transfer object for users.
│   │               ├── exceptions
│   │               │   ├── InsufficientBalanceException.java
│   │               │   │       # Exception thrown for insufficient balance.
│   │               │   ├── TransactionNotFoundException.java
│   │               │   │       # Exception thrown when a transaction is not found.
│   │               │   └── UserNotFoundException.java
│   │               │       # Exception thrown when a user is not found.
│   │               ├── Main.java
│   │               ├── models
│   │               │   ├── ErrorDetails.java
│   │               │   │       # Represents details of an error response.
│   │               │   ├── Transaction.java
│   │               │   │       # Entity model for transactions, mapped to the database table.
│   │               │   └── User.java
│   │               │       # Entity model for users, mapped to the database table.
│   │               ├── repositories
│   │               │   ├── TransactionRepository.java
│   │               │   │       # Interface for interacting with the transaction database table.
│   │               │   └── UserRepository.java
│   │               │       # Interface for interacting with the user database table.
│   │               └── services
│   │                   ├── TransactionServiceImpl.java
│   │                   │       # Implementation for transaction-related services.
│   │                   ├── TransactionService.java
│   │                   │       # Interface for transaction-related services.
│   │                   ├── UserServiceImpl.java
│   │                   │       # Implementation for user-related services.
│   │                   └── UserService.java
│   │                       # Interface for user-related services.
│   └── resources
│       ├── application.properties
│       │       # Application-specific properties, such as database configuration.
│       ├── data.sql
│       │       # SQL statements for initializing or seeding the database with initial data.
│       └── schema.sql
│               # SQL statements for defining the database schema.
└── test
    └── java
        └── com
            └── example
                └── wallet
                    └── services
                        ├── TransactionServiceImplTest.java
                        │       # Unit tests for TransactionServiceImpl class.
                        └── UserServiceImplTest.java
                                # Unit tests for UserServiceImpl class.
```

## Exposed API Endpoints

- **Transactions**
  - `POST /transactions`: Create a new transaction.
  - `GET /transactions/{id}`: Get a transaction by ID.
  - `GET /transactions/user/{userId}`: Get all transactions for a user.
  - `GET /transactions/`: Get all transactions.
- **Users**
  - `POST /users`: Create a new user.
  - `GET /users/{id}`: Get a user by ID.
  - `GET /users/username/{username}`: Get a user by username.
  - `GET /users`: Get all users.

## Test Application

Here are examples of how to test the project using [cURL](https://curl.se/) and use
[jq](https://github.com/jqlang/jq) for beauty output:

```sh
# Create new user
curl -X POST http://localhost:8080/api/users \
-H 'Content-Type: application/json' \
-d '{
    "username": "readme_user", 
    "balance": 1000.00 
}' \
-s | jq


# Get specific user by id
curl http://localhost:8080/api/users/1 -s | jq


# Or get user by username
curl http://localhost:8080/api/users/username/readme_user -s | jq


# Get all users
curl http://localhost:8080/api/users -s | jq


# Transfer balance with amount = 500 from user with id = 1 to user with id = 2
# first check current balance in two users
curl http://localhost:8080/api/users/1 -s | jq
curl http://localhost:8080/api/users/2 -s | jq


# Transfer money
curl -X POST http://localhost:8080/api/transactions/transfer \
-H 'Content-Type: application/json' \
-d '{
    "senderId": 1, 
    "receiverId": 2,
    "amount": 200
}' \
-s | jq

# then check current balance in two users
curl http://localhost:8080/api/users/1 -s | jq
curl http://localhost:8080/api/users/2 -s | jq


# Get specific transaction by id
curl http://localhost:8080/api/transactions/1 -s | jq


# Or get transactions for specific user by id 
curl http://localhost:8080/api/transactions/user/1 -s | jq


# Get all transactions
curl http://localhost:8080/api/transactions -s | jq
```

## License

This project is licensed under the MIT License - see the [LICENSE](./LICENSE) file for details.
