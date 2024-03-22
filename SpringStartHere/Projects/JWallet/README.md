# JWallet

- [JWallet](#jwallet)
  - [Project Structure](#project-structure)
  - [Technologies Used](#technologies-used)
  - [Architecture Overview](#architecture-overview)
    - [Business Logic Layer](#business-logic-layer)
    - [Data Access Layer](#data-access-layer)
    - [Database Layer](#database-layer)
      - [Database Schema](#database-schema)
  - [Getting Started](#getting-started)
    - [Exposed API Endpoints](#exposed-api-endpoints)
    - [Application Usage](#application-usage)
  - [References](#references)
  - [License](#license)

The JWallet Application is a Java Spring Boot backend project designed to manage Wallet transactions. It
utilizes the MySQL database and Spring Data JPA. The project follows the MVC pattern and includes unit tests for
comprehensive testing. The purpose of this project is to demonstrate basic CRUD operations with Spring Data,
unit testing, and showcase exception handling techniques.

## Project Structure

The project follows the MVC (Model-View-Controller) architecture pattern and is structured as follows:

```bash
JWallet/
└── src/
    ├── main/
    │   ├── java/
    │   │   └── com/
    │   │       └── example/
    │   │           └── wallet/
    │   │               ├── config/
    │   │               │   └── ProjectConfig.java
    │   │               ├── controllers/ # Contains controller classes for handling HTTP requests
    │   │               │   ├── advice/
    │   │               │   │   └── GlobalExceptionHandle.java
    │   │               │   ├── TransactionController.java
    │   │               │   └── UserController.java
    │   │               ├── dto/ # Data Transfer Objects used for transferring data between layers
    │   │               │   ├── TransactionDTO.java
    │   │               │   ├── TransferDTO.java
    │   │               │   ├── UpdateBalanceDTO.java
    │   │               │   └── UserDTO.java
    │   │               ├── exceptions/
    │   │               │   ├── InsufficientBalanceException.java
    │   │               │   ├── TransactionNotFoundException.java
    │   │               │   └── UserNotFoundException.java
    │   │               ├── Main.java
    │   │               ├── models/ # Entity classes representing project data
    │   │               │   ├── ErrorDetails.java
    │   │               │   ├── Transaction.java
    │   │               │   └── User.java
    │   │               ├── repositories/ # Contains repository interfaces for database operations
    │   │               │   ├── TransactionRepository.java
    │   │               │   └── UserRepository.java
    │   │               └── services/ # Contains service classes for business logic
    │   │                   ├── TransactionServiceImpl.java
    │   │                   ├── TransactionService.java
    │   │                   ├── UserServiceImpl.java
    │   │                   └── UserService.java
    │   └── resources/
    │       ├── application.properties
    │       ├── data.sql
    │       ├── schema.sql
    └── test/
        └── java/
            └── com/
                └── example/
                    └── wallet/
                        └── services/ # Contains unit tests for repository layer
                            ├── TransactionServiceImplTest.java
                            └── UserServiceImplTest.java

```

## Technologies Used

| Name                                          | Version                                      |
| --------------------------------------------- | -------------------------------------------- |
| **Java**                                      | `<java.version>17</java.version>`            |
| **Spring Boot**                               | `<version>3.2.3</version>`                   |
| **Spring Data JPA**                           | `<version>{Let it for Spring Boot}<version>` |
| **MySql database**                            | `11.3.2-MariaDB (For Arch Linux users)`       |
| **mysql-connector-j** (MySQL Driver for Java) | `<version>{Let it for Spring Boot}<version>` |
| **Maven** (for dependency management)         | `<modelVersion>4.0.0</modelVersion>`         |

## Architecture Overview

JWallet follows a standard architecture pattern to organize its components effectively. Below is an overview
of the architecture components:

### Business Logic Layer

The business logic layer contains service classes (`UserService`, `TransactionService`) responsible for
implementing the application's business rules. These services interact with the data access layer to perform
CRUD and transactions operations.

### Data Access Layer

The data access layer consists of repository interfaces (`UserRepository`, `TransactionRepository`) that
define methods for interacting with the underlying database.

Spring Data JPA is used to provide a high-level abstraction over the database, making it easier to perform
database operations.

### Database Layer

JWallet uses a MySQL database to store data and Spring Data JPA. MySQL is a popular open-source relational
database management system (RDBMS).

JWallet uses a Spring Data JPA to interact with the MySQL database. Spring Data JPA simplifies layer on top of
JPA (Java Persistence API) specifically for relational databases such as MySQL.

#### Database Schema

The schema for the MySql database includes a two tables named **'users'** and **'transactions'** with the
following columns:

- **Users** Table:

This table stores information about the users of the JWallet application.

| Field Name | Data Type      | Description                                        | Not Null | Primary Key | Foreign Key |
| ---------- | -------------- | -------------------------------------------------- | -------- | ----------- | ----------- |
| id         | BIGINT         | Unique identifier for the user (auto-incrementing) | YES      | YES         |             |
| username   | VARCHAR(255)   | Username for the user account                      | YES      |             |             |
| balance    | DECIMAL(19, 2) | Current balance of the user in the wallet          | YES      |             |             |

- **transactions** Table:

This table stores information about all the financial transactions that occur within the JWallet application.

| Column Name | Data Type      | Description                                                       | Not Null | Primary Key | Foreign Key |
| ----------- | -------------- | ----------------------------------------------------------------- | -------- | ----------- | ----------- |
| id          | BIGINT         | Unique identifier for the transaction (auto-incrementing)         | YES      | YES         |             |
| sender_id   | BIGINT         | Foreign key referencing the user who sent the money               | YES      |             | USERS(id)   |
| receiver_id | BIGINT         | Foreign key referencing the user who received the money           | YES      |             | USERS(id)   |
| amount      | DECIMAL(19, 2) | Amount of money transferred in the transaction                    | YES      |             |             |
| created_at  | TIMESTAMP      | Timestamp of when the transaction was created (automatically set) | YES      |             |             |

## Getting Started

To run the JWallet application locally, follow these steps:

1. Clone this project or repository.
2. Run MySql server and create your database.
3. Navigate to the project directory: `cd JWallet`
4. Run the application: `./mvnw spring-boot:run`

    ```bash
    ./mvnw spring-boot:run \
        -Dspring-boot.run.arguments="
        --SPRING_DATASOURCE_URL={your_spring_datasource_url}
        --SPRING_DATASOURCE_USERNAME={your_spring_datasource_username}
        --SPRING_DATASOURCE_PASSWORD={your_spring_datasource_password}
    "
    ```

    If you want to debug this project using the VSCodium (Open Source Binaries of VS Code) editor, you will
    need to create a `luanch.json' file and add the following code to it:

    ```json
    {
        // ...
        "configurations": [
            {
                "name": "SpringBoot",
                "type": "java",
                "request": "launch",
                "mainClass": "com.example.wallet.Main", // add your main class.
                "env": {
                    "SPRING_DATASOURCE_USERNAME": "{your_spring_datasource_username}"
                    "SPRING_DATASOURCE_PASSWORD": "{your_spring_datasource_password}"
                    "SPRING_DATASOURCE_URL": "{your_spring_datasource_url}"
                },
            },
        ]
        // ...
    }
    ```

    Make sure to replace {your_spring_datasource_url}, {your_spring_datasource_username}, and
    {your_spring_datasource_password} with your actual MySQL server values.

The application will be accessible by default at `http://localhost:8080`.

### Exposed API Endpoints

- **Users**
  - `POST /users`: Create a new user.
  - `GET /users/{id}`: Get a user by ID.
  - `GET /users/username/{username}`: Get a user by username.
  - `GET /users`: Get all users.

- **Transactions**
  - `POST /transactions`: Create a new transaction.
  - `GET /transactions/{id}`: Get a transaction by ID.
  - `GET /transactions/user/{userId}`: Get all transactions for a user.
  - `GET /transactions/`: Get all transactions.

### Application Usage

Here are examples of using the JWallet API with [cURL](https://curl.se/) and using
[jq](https://github.com/jqlang/jq) to get a nice output format:

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

## References

- Spring Docs: <https://docs.spring.io/spring-framework/reference/index.html>
- Accessing data with MySQL (Spring Docs): <https://spring.io/guides/gs/accessing-data-mysql>
  - Github for source code: <https://github.com/spring-guides/gs-accessing-data-mysql>
- Connect Java to a MySQL Database tutorial: <https://www.baeldung.com/java-connect-mysql>
- Pass environment variables to Spring Boot application with Maven: <https://stackoverflow.com/a/63853229/13377098>

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
