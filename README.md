## Project Overview

This project is a Spring Boot application designed for managing client registrations. 
It provides RESTful endpoints to register new clients, retrieve client information by ID, 
and handle common errors related to user management.

### Technology Stack ### 
- **Java 21:** Programming language used for development.
- **Spring Boot 3.3.2:** Framework for building the RESTful services.
- **Spring Data JPA:** For ORM and database interactions.
- **Hibernate:** ORM framework for mapping Java objects to database tables.
- **Lombok:** Library to reduce boilerplate code with annotations like @Getter, @Setter, @Builder, etc.
- **PostgreSQL:** Relational database (runtime dependency).
- **H2 Database:** In-memory database used for testing.
- **Jakarta Validation:** For input validation in ClientRegistrationRequest.
- **Spring Security:** For security configurations (runtime dependency).
- **JUnit 5:** For unit testing the application.
- **Jacoco:** Code coverage tool integrated with Maven.

### Project Structure

- **entity/ClientApp.java:** Entity representing a client with attributes such as id, userName, email, password, etc. 
    Includes JPA annotations for ORM mapping.
- **controller/ClientController.java:** REST controller managing client-related endpoints for registration and retrieval by ID.
- **service/ClientService.java:** Service layer containing business logic for client registration and retrieval.
- **request/ClientRegistrationRequest.java:** Record for client registration requests with validation constraints.
- **repository/ClientRepository.java:** Spring Data JPA repository for CRUD operations on ClientApp.
- **exception/EmailAlreadyExistsException.java, UserAlreadyExistsException.java, UserNotFoundException.java:**
   Custom exceptions for handling specific error scenarios.
- **controller/ClientControllerTest.java:** Unit tests for the ClientController, validating registration and retrieval functionalities.
- **controller/ClientControllerExceptionTest.java:** Unit tests focusing on exception handling scenarios.

### API Endpoints
1. **Register a New Client**  
    URL: POST /api/v1/clients/registrations  
    Request Body:  
   \```{  
  "username": "uniqueUsername",    
  "email": "user@example.com",    
  "password": "YourPassword123!"  
   }  
   \```
- **Responses:**  
  **201 Created:** Returns the UUID of the newly created client.  
  **400 Bad Request:** Returns error messages if the request body is invalid or if the username/email already exists.


2. **Retrieve Client by ID**  
  URL: GET /api/v1/clients/{id}  
- **Responses:**  
  **200 OK:** Returns the details of the client with the given ID.  
  **404 Not Found:** Returns an error message if no client is found with the given ID.  

### Validation Rules
1. **Username:**
- Must not be empty. 
- Must contain only letters.

2. **Email:**
- Must not be empty. 
- Must be a valid email format.

3. **Password:**
- Must not be empty. 
- Must contain between 5 and 20 characters. 
- Must include at least one uppercase letter, one lowercase letter, one digit, and one special character.

### Running Tests
The project includes unit tests to validate the functionalities of the ClientController and error handling.

![Testing Jacoco](user-service/Testing%20Jacoco%20.jpg)

To run the tests, use the following Maven command:  
\````./mvnw test ```

### Getting Started
**Prerequisites**
- **Java 21:** Ensure Java 21 is installed on your machine.
- **Maven:** Maven is used for building the project.

### Running the Application
1. Clone the repository:  
\````git clone git@github.com:eusebiujacot/taxi-application.git```  
2. Build and run the application:  
\````./mvnw spring-boot:run```
3. Access the API using a tool like Postman or curl.

### Maven Configuration
The project uses Maven for build and dependency management. 
Key configurations in pom.xml:
- **Parent:** Spring Boot Starter Parent for dependency management.
- **Properties:** Java version set to 21, and Jacoco version for code coverage.
- **Dependencies:** Includes Spring Boot starters, PostgreSQL driver, H2 database for testing, Lombok, and MapStruct.

**Plugins:**
- **Spring Boot Maven Plugin:** For running and packaging the application.
- **Jacoco Maven Plugin:** For generating code coverage reports.















