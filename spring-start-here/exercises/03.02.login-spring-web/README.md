# Spring Login Web App

This is a simple web application built with Java and the Spring framework, focusing on the MVC architecture.
The project includes functionalities for user authentication, registration, and a basic user listing feature.
It is specifically designed for educational purposes, providing hands-on experience with Spring web
development, Spring Boot, and understanding Spring bean scopes and life cycle.

## Project Structure

The project follows a standard Maven directory structure:

```text
src
└── main
    ├── java
    │   └── com
    │       └── login
    │           └── springweb
    │               ├── controllers
    │               │   ├── LoginController.java
    │               │   ├── MainController.java
    │               │   └── RegisterController.java
    │               ├── models
    │               │   └── User.java
    │               ├── repositories
    │               │   ├── UserRepository.java
    │               │   └── UserRepositoryImpl.java
    │               ├── services
    │               │   ├── AuthenticationService.java
    │               │   ├── AuthenticationServiceImpl.java
    │               │   ├── OnlineUserService.java
    │               │   ├── OnlineUserServiceImpl.java
    │               │   ├── SessionService.java
    │               │   ├── SessionServiceImpl.java
    │               │   ├── UserService.java
    │               │   └── UserServiceImpl.java
    │               └── Main.java
    └── resources
        ├── static
        │   └── css
        │       └── styles.css
        └── templates
            ├── home.html
            ├── login.html
            └── register.html
```

### File Descriptions

- **Controllers:**
  - **LoginController.java, MainController.java, RegisterController.java:**
    - These files contain the controllers responsible for handling different web requests.
    - `LoginController`: Manages login-related requests.
    - `MainController`: Handles main page requests and redirects.
    - `RegisterController`: Handles user registration requests.

- **Models:**
  - **User.java:**
    - Represents the User model with fields for username, password, and favorite color.

- **Repositories:**
  - **UserRepository.java, UserRepositoryImpl.java:**
    - `UserRepository`: Defines the interface for user-related data access operations.
    - `UserRepositoryImpl`: Implements the user repository, possibly using an in-memory data store.

- **Services:**
  - **AuthenticationService.java, AuthenticationServiceImpl.java:**
    - `AuthenticationService`: Interface for authentication-related operations.
    - `AuthenticationServiceImpl`: Implements authentication logic.

  - **OnlineUserService.java, OnlineUserServiceImpl.java:**
    - `OnlineUserService`: Interface for managing online user counts.
    - `OnlineUserServiceImpl`: Implements the logic for tracking online users.

  - **SessionService.java, SessionServiceImpl.java:**
    - `SessionService`: Interface for managing user sessions.
    - `SessionServiceImpl`: Implements session-related operations.

  - **UserService.java, UserServiceImpl.java:**
    - `UserService`: Interface for user-related operations.
    - `UserServiceImpl`: Implements user-related business logic.

- **Main:**
  - **Main.java:**
    - Contains the main entry point for the Spring Boot application.

- **Resources:**
  - **Static:**
    - **css:**
      - **styles.css:**
        - Contains the styles for the web application.

  - **Templates:**
    - **home.html, login.html, register.html:**
      - Thymeleaf templates for rendering the home, login, and register pages.

## Features

- **User Authentication:** Allows users to log in with a username and password.
- **User Registration:** Provides a simple form for user registration with a username, password, and favorite
color.
- **User Listing:** Displays a list of registered users.

## Educational Benefits

- **Hands-on Spring Web Development:** Learn the basics of building web applications using the Spring framework.
- **Introduction to Spring Boot:** Explore the benefits and conventions of Spring Boot for simplifying
application development.
- **Understanding Bean Scopes and Life Cycle:** Dive into the fundamentals of Spring beans, their scopes, and
life cycle.

## Technologies Used

- Java
- Spring MVC
- Spring Boot
- Thymeleaf (for templating)
- HTML/CSS
- Bootstrap (for styling)

## Usage

1. Clone the repository.
2. Open the project in your favorite Java IDE.
3. Run the application (`Main.java`).
4. Access the application at `http://localhost:8080`.

## License

This project is licensed under the [MIT License](LICENSE).
