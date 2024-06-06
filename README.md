
# Instagram Clone Backend

This is the backend of an Instagram clone project built with Spring Boot, providing REST APIs for user authentication, user management, post creation, and commenting.

## Project Structure

The project is structured as follows:

- **config**: Contains configuration classes for JWT token generation and validation, as well as security context.
- **controller**: Defines REST controllers for handling authentication and user-related operations.
- **dto**: Data transfer objects (DTOs) for transferring user data between the client and the server.
- **exceptions**: Custom exception classes for handling errors and generating error responses.
- **model**: Entity classes representing users, posts, comments, and stories.
- **repository**: Spring Data JPA repositories for database interaction.
- **response**: Response classes for returning messages and error details.
- **security**: Classes related to JWT token generation, claims, and provider.
- **service**: Service interfaces and implementations for business logic implementation.
- **InstagramApiApplication**: Main Spring Boot application class.

## Technologies Used

- **Spring Boot**: Provides the framework for building the backend application.
- **Spring Data JPA**: Simplifies data access and persistence.
- **Spring Security**: Handles authentication and authorization.
- **JWT (JSON Web Tokens)**: Used for securing RESTful APIs.
- **MySQL**: Database management system for data storage.
- **Java 17**: Programming language for backend development.

## Dependencies

- **Spring Boot Starter Data JPA**: `spring-boot-starter-data-jpa`
- **Spring Boot Starter Validation**: `spring-boot-starter-validation`
- **Spring Boot Starter Web**: `spring-boot-starter-web`
- **Spring Boot DevTools**: `spring-boot-devtools`
- **MySQL Connector/J**: `mysql-connector-j`
- **Spring Boot Starter Test**: `spring-boot-starter-test`
- **Spring Security**: `spring-boot-starter-security`
- **Spring Security Cryptography**: `spring-security-crypto`
- **JWT API**: `jjwt-api` (Version 0.11.1)
- **JWT Implementation**: `jjwt-impl` (Version 0.11.1)
- **JWT Jackson**: `jjwt-jackson` (Version 0.11.2)

## Getting Started

1. Clone the repository:

```bash
git clone https://github.com/your-username/instagram-api.git
```

2. Set up a MySQL database and configure the database connection properties in `application.properties`.

3. Run the application:

```bash
./mvnw spring-boot:run
```

4. Access the API endpoints using a REST client or integrate them with your frontend application.

