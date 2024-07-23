# JWallet

The JWallet application is a Java Spring Boot project designed to facilitate the management of wallet
transactions. The JWallet project is a practical application based on the "**Spring Start Here by Laurentiu
Spilca**: <https://www.manning.com/books/spring-start-here>" and serves to demonstrate the use of Spring Boot
for the construction of a web application with Spring MVC, Spring Data JPA for database interactions, and
Maven for project management.

JWallet is configured to work with a MySQL database, includes Docker support for containerization, and Swagger
API for documentation. Additionally, it employs a multi-profile approach for different environments
(development and production).

## Technology Stack

- **Spring Boot**: Framework for building production-ready applications.
- **Spring MVC**: Web framework for building RESTful APIs and web applications.
- **Spring Data JPA**: A library for simplifying data access and management using Java Persistence API (JPA),
  offering a repository-based approach to interact with databases.
- **Spring Boot DevTools**: A set of tools that provide an enhanced development experience with features like
  automatic restarts, live reload, and configurations for fast feedback during development.
- **Maven**: Build automation tool that manages project builds, and dependencies.
- **Lombok**: A Java library that helps reduce boilerplate code by generating commonly used methods.
- **MySQL**: Relational database (for production).
- **H2**: In-memory database (for development).
- **Swagger API**: A powerful tool for documenting and designing RESTful APIs.
- **Docker**: Containerization platform.

## Profiles

The project uses different Spring and Maven (see [`pom.xml`](./pom.xml)) profiles to manage configurations for
development and production environments:

### Development Profile (`dev`)

- **Database**: Uses H2 in-memory database.
- **Configuration**: Development-specific settings, including Spring Boot DevTools for automatic restarts and live reload.
- **Properties File**: [`src/main/resources/application-dev.properties`](./src/main/resources/application-dev.properties)

### Production Profile (`prod`)

- **Database**: Connects to MySQL database.
- **Configuration**: Production-specific settings, ensuring optimal performance and security.
- **Properties File**: [`src/main/resources/application-prod.properties`](./src/main/resources/application-prod.properties)
- Default Profile.

## Getting Started

To get started with the JWallet, follow these steps:

1. **Clone this project or repository.**
2. **Run with Docker Compose:** (by default `prod` profile is running)
   - Navigate to the project directory and run the following command:

   ```bash
   docker compose up
   ```

3. **Accessing the Application**:
   - The application will be accessible at <http://localhost:8080>.
   - Swagger API at <http://localhost:8080/api-ui.html>.
   - API endpoints exposed at <http://localhost:8080/api/>.

## Build and Debug

### Build

1. **Navigate to the Source Code Directory**.
2. **Build and Run the Application (without Docker)**:

   1. **For development Profile (`dev`)**:

      ```bash
      ./mvnw -Pdev spring-boot:run -Dspring-boot.run.profiles=dev
      ```

   1. **For Production Profile (`prod`)**:
      - Ensure the MySQL database server is running.
      - Use the following command, replacing `{your_datasource_url}`, `{your_datasource_username}`, and
        `{your_datasource_password}` with your actual MySQL server details:

        ```bash
        ./mvnw -Pprod spring-boot:run -Dspring-boot.run.profiles=prod -Dspring-boot.run.arguments="
            --SPRING_DATASOURCE_URL={your_datasource_url}
            --SPRING_DATASOURCE_USERNAME={your_datasource_username}
            --SPRING_DATASOURCE_PASSWORD={your_datasource_password}
        "
        ```

### Debug

To debug this project using [VSCodium](https://vscodium.com/) (Open Source Binaries of VS Code) editor:

1. **Create a `launch.json` File:**
   1. Navigate to the `.vscode` directory in your project's root (create it if it doesn't exist).
   2. Add the following configuration:

      ```json
      {
          "configurations": [
              {
                  "type": "java",
                  "name": "SpringBoot (Dev)",
                  "request": "launch",
                  "cwd": "${workspaceFolder}",
                  "mainClass": "com.project.jwallet.Main",
                  "projectName": "jwallet",
                  "env": {
                      "SPRING_PROFILES_ACTIVE": "dev"
                  }
              },
              {
                  "type": "java",
                  "name": "SpringBoot (Prod)",
                  "request": "launch",
                  "cwd": "${workspaceFolder}",
                  "mainClass": "com.project.jwallet.Main",
                  "projectName": "jwallet",
                  "env": {
                      "SPRING_PROFILES_ACTIVE": "prod",
                      "SPRING_DATASOURCE_URL": "{your_datasource_url}",
                      "SPRING_DATASOURCE_USERNAME": "{your_datasource_username}",
                      "SPRING_DATASOURCE_PASSWORD": "{your_datasource_password}",
                  }
              }
          ]
      }
      ```

      - Replace `{your_datasource_url}`, `{your_datasource_username}`, and `{your_datasource_password}` with
        your actual MySQL server details for the production configuration.
2. **Set Maven Profile in VSCodium:**
   - Open Command Palette (`Ctrl+Shift+P`).
   - Search for `Java: Open Project Settings`.
   - Navigate to `Maven > Set your profile` and select the profile you want to debug.
3. **Start Debugging:**
   - Select the desired debug configuration (e.g., `SpringBoot (Dev)`) and start debugging by clicking the green play button or pressing `F5`.

### Run Tests

To run the unit and integration tests, use the following Maven command:

```bash
./mvnw clean test
```

## Project Structure

The project follows the MVC (Model-View-Controller) architecture pattern and is structured as follows:

```bash
JWallet/
└── src/
    ├── main/
    │   ├── java/
    │   │   └── com/
    │   │       └── project/
    │   │           └── jwallet/
    │   │               ├── config/
    │   │               │   └── ProjectConfig.java
    │   │               ├── controllers/ # Contains controller classes for handling HTTP requests
    │   │               │   ├── advice/
    │   │               │   │   └── GlobalExceptionHandle.java
    │   │               │   ├── HomeController.java
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
    │   │               ├── models/ # Entity classes representing project data
    │   │               │   ├── ErrorDetails.java
    │   │               │   ├── Transaction.java
    │   │               │   └── User.java
    │   │               ├── repositories/ # Contains repository interfaces for database operations
    │   │               │   ├── TransactionRepository.java
    │   │               │   └── UserRepository.java
    │   │               ├── services/ # Contains service classes for business logic
    │   │               │   ├── TransactionServiceImpl.java
    │   │               │   ├── TransactionService.java
    │   │               │   ├── UserServiceImpl.java
    │   │               │   └── UserService.java
    │   │               └── Main.java
    │   └── resources/
    │       ├── static/
    │       ├── templates/
    │       ├── application-dev.properties
    │       ├── application-prod.properties
    │       ├── application.properties
    │       └── data.sql
    └── test/
        └── java/
            └── com/
                └── project/
                    └── jwallet/
                        └── services/ # Contains unit tests for repository layer
                            ├── TransactionServiceImplTest.java
                            └── UserServiceImplTest.java
```

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

## References

- **Spring Docs:** <https://docs.spring.io/spring-framework/reference/index.html>
- **Database:**
  - Database Initialization (Spring Docs): <https://docs.spring.io/spring-boot/how-to/data-initialization.html>
  - Accessing data with MySQL (Spring Docs): <https://spring.io/guides/gs/accessing-data-mysql>
    - Github for source code: <https://github.com/spring-guides/gs-accessing-data-mysql>
  - Connect Java to a MySQL Database tutorial (Baeldung): <https://www.baeldung.com/java-connect-mysql>
  - Spring Boot With H2 Database (Baeldung): <https://www.baeldung.com/spring-boot-h2-database>
  - Spring Boot with H2 Database (HowToDoInJava): <https://howtodoinjava.com/spring-boot/h2-database-example>
- **SpringDoc (Swagger):**
  - OpenAPI 3 Library for spring-boot: <https://springdoc.org/#getting-started>
  - Spring Boot 3 OpenAPI Docs with Springdoc and Swagger (HowToDoInJava): <https://howtodoinjava.com/spring-boot/springdoc-openapi-rest-documentation>
- **Spring DevTool:**
  - Developer Tools (Spring Docs): <https://docs.spring.io/spring-boot/reference/using/devtools.html>
  - Overview of Spring Boot Dev (Baeldung): <https://www.baeldung.com/spring-boot-devtools>
- **Run and Debug:**
  - Running Application with Maven (Spring Docs): <https://docs.spring.io/spring-boot/maven-plugin/run.html>
  - Debugging Spring Applications (Baeldung): <https://www.baeldung.com/spring-debugging>
  - Pass environment variables to Spring Boot application with Maven: <https://stackoverflow.com/a/63853229/13377098>

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
