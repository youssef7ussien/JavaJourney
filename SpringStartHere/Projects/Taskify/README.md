# Taskify

Taskify is a Task Management System developed with Java Spring Boot that provides full CRUD functionality.
Users can seamlessly create, update, and prioritize tasks through an intuitive user interface. The system
utilizes the power of Spring Boot to efficiently handle task operations. The application uses an H2 in-memory
database to store task data.

## Project Structure

The project follows the MVC (Model-View-Controller) architecture pattern and is structured as follows:

```bash
taskify/
└── src/
    ├── main/
    │   ├── java/
    │   │   └── com/
    │   │       └── example/
    │   │           └── taskify/
    │   │               ├── config/
    │   │               │   └── ProjectConfig.java
    │   │               ├── controllers/ # Contains controller classes for handling HTTP requests
    │   │               │   ├── advice/
    │   │               │   │   └── ExceptionHandlerController.java
    │   │               │   └── TaskController.java
    │   │               ├── dto/ # Data Transfer Objects used for transferring data between layers
    │   │               │   └── TaskDTO.java
    │   │               ├── exceptions/
    │   │               │   └── TaskNotFoundException.java
    │   │               ├── models/ # Entity classes representing task data
    │   │               │   ├── ErrorDetails.java
    │   │               │   └── Task.java
    │   │               ├── repositories/ # Contains repository interfaces for database operations
    │   │               │   └── TaskRepository.java
    │   │               ├── services/ # Contains service classes for business logic
    │   │               │   ├── TaskServiceImpl.java
    │   │               │   └── TaskService.java
    │   │               └── TaskifyApplication.java # Spring Boot application main class
    │   └── resources/
    │       ├── application.properties
    │       ├── schema.sql
    │       ├── static/ # Contains static resources (e.g., CSS, JavaScript)
    │       │   └── css/
    │       │       └── index.css
    │       └── templates/ # Contains Thymeleaf templates for HTML views
    │           ├── index.html
    │           ├── not-found.html
    │           └── tasks-item.html
    └── test/
        └── java/
            └── com/
                └── example/
                    └── taskify/
                        ├── repositories/ # Contains unit tests for repository layer
                        │   └── TaskRepositoryTests.java
                        └── services/ # Contains unit tests for service layer
                            └── TaskServiceImplTest.java
```

## Technologies Used

| Name                                                   | Version                                      |
| ------------------------------------------------------ | -------------------------------------------- |
| **Java**                                               | `<java.version>17</java.version>`            |
| **Spring Boot**                                        | `<version>3.2.3</version>`                   |
| **Spring Data JPA**                                    | `<version>{Let it for Spring Boot}<version>` |
| **ModelMapping** (for mapping between models and DTOs) | `<version>3.0.0</version>`                   |
| **H2 database**                                        | `<version>{Let it for Spring Boot}<version>` |
| **Thymeleaf** (for server-side rendering)              | `<version>{Let it for Spring Boot}<version>` |
| **Maven** (for dependency management)                  | `<modelVersion>4.0.0</modelVersion>`         |

## Architecture Overview

Taskify follows a standard architecture pattern to organize its components effectively. Below is an overview
of the architecture components:

### Presentation Layer (UI)

The project's main focus is not on the user interface. Therefore, the interface was obtained from the "TodoMVC
project (<https://todomvc.com>)", which provides a clean and responsive design for task management (Certainly,
I made some modifications to it).

The use of the TodoMVC project) allows the project to focus on the learning objectives related to backend
development and Spring Boot framework implementation.

### Business Logic Layer

The business logic layer contains service classes (`TaskService`) responsible for implementing the
application's business rules. These services interact with the data access layer to perform CRUD operations on
tasks.

### Data Access Layer

The data access layer consists of repository interfaces (`TaskRepository`) that define methods for interacting
with the underlying database.

Spring Data JPA is used to provide a high-level abstraction over the database, making it easier to perform
database operations.

### Database Layer

Taskify uses an H2 in-memory database to store task data. H2 is a lightweight and embeddable database that
provides JDBC and JPA support.

#### Database Schema

The schema for the H2 database includes a single table named **'Task'** with the following columns:

| key            | type                 | description                                                   |
| :------------- | :------------------- | ------------------------------------------------------------- |
| id             | BIGINT (Primary Key) | Unique identifier for each task.                              |
| title          | VARCHAR(255)         | Descriptive title of the task.                                |
| description    | VARCHAR(1000)        | Additional details or notes related to the task.              |
| created_date   | TIMESTAMP            | Timestamp indicating when the task was created.               |
| completed_date | TIMESTAMP            | Timestamp indicating when the task was completed.             |
| completed      | Boolean              | Boolean flag indicating whether the task is completed or not. |

## Getting Started

To run the Taskify application locally, follow these steps:

1. Clone this project or  repository.
2. Navigate to the project directory: `cd taskify`
3. Run the application: `./mvnw spring-boot:run`

The application will be accessible at `http://localhost:8080` and the H2 database will be accessible at
`http://localhost:8080/h2-console`

## References

- TodoMVC Project: <https://todomvc.com>
- Thymeleaf Project: <https://www.thymeleaf.org/doc/tutorials/3.1/usingthymeleaf.html>
- Spring Docs: <https://docs.spring.io/spring-framework/reference/index.html>
- Build todo with Spring Boot tutorial: <https://www.wimdeblauwe.com/blog/2021/09/20/todomvc-with-spring-boot-and-thymeleaf-part-1/>
- H2 Database tutorial: <https://www.baeldung.com/spring-boot-h2-database>

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
