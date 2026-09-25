# Fitness Tracker API 
**(Live Link : [Open Swagger UI](https://fitness-tracker-api-j1i5.onrender.com/swagger-ui/index.html#/))**

A production-oriented RESTful fitness tracking backend built with **Java, Spring Boot, Spring Data JPA, Spring Security, and PostgreSQL**.

The API provides user authentication, activity tracking, and personalized fitness recommendations.

## Features

* User registration and authentication
* Stateless JWT-based authentication
* Role-based authorization
* Secure password hashing using BCrypt
* Fitness activity tracking
* Activity-based recommendations
* Request validation
* Centralized exception handling
* PostgreSQL database integration
* Interactive Swagger API documentation
* Dockerized application
* Cloud deployment using Render and Neon

## Tech Stack

* **Language:** Java 21
* **Framework:** Spring Boot
* **Security:** Spring Security, OAuth2 Resource Server, JWT
* **Persistence:** Spring Data JPA, Hibernate
* **Database:** PostgreSQL
* **Build Tool:** Maven
* **Containerization:** Docker
* **Deployment:** Render
* **Cloud Database:** Neon
* **API Documentation:** Swagger / OpenAPI

## System Design

[//]: # (> Add the Excalidraw system-design diagram here.)

<!-- ![System Design](docs/system-design.png) -->

The diagram illustrates the overall application architecture, including:

* Controllers
* Services
* Repositories
* Entities
* DTOs
* Security components
* Authentication flow
* Database interaction

## Authentication & Authorization

The API uses **stateless JWT-based authentication with Spring Security**.

* User credentials are authenticated through Spring Security.
* Passwords are secured using **BCrypt**.
* Successful login returns a JWT.
* JWTs are used to authenticate subsequent requests.
* Custom JWT claims are mapped to application authorities.
* Protected endpoints use role-based authorization.
* Spring Security OAuth2 Resource Server is used for JWT-based request authentication.

## API Endpoints

### Authentication

| Method | Endpoint             | Description                          |
| ------ | -------------------- | ------------------------------------ |
| `POST` | `/api/auth/register` | Register a new user                  |
| `POST` | `/api/auth/login`    | Authenticate a user and obtain a JWT |

### Activities

| Method | Endpoint          | Description           |
| ------ | ----------------- | --------------------- |
| `GET`  | `/api/activities` | Retrieve activities   |
| `POST` | `/api/activities` | Create a new activity |

### Recommendations

| Method | Endpoint                                    | Description                              |
| ------ | ------------------------------------------- | ---------------------------------------- |
| `POST` | `/api/recommendation/generate`              | Generate a recommendation                |
| `GET`  | `/api/recommendation/user/{userId}`         | Retrieve recommendations for a user      |
| `GET`  | `/api/recommendation/activity/{activityId}` | Retrieve recommendations for an activity |

## Database

The application uses **PostgreSQL** with **Spring Data JPA and Hibernate**.

The main domain models are:

* `User`
* `Activity`
* `Recommendation`

### Database Structure

[//]: # (> Add the Excalidraw database diagram here.)

<!-- ![Database Structure](docs/database-structure.png) -->

The diagram illustrates the relationships between the `User`, `Activity`, and `Recommendation` entities along with their key attributes.

## API Documentation

Interactive API documentation is available through **Swagger UI**.

**[Open Swagger UI](https://fitness-tracker-api-j1i5.onrender.com/swagger-ui/index.html#/)**

Swagger can be used to explore:

* Available endpoints
* Request parameters
* Request bodies
* Response structures
* Authentication requirements

## Running Locally

### Prerequisites

Make sure the following are installed:

* Java 21
* Maven
* PostgreSQL

### Configuration

Configure the required database and JWT properties using environment variables.

```env
DATABASE_URL=jdbc:postgresql://<host>:5432/<database>
DATABASE_USERNAME=<username>
DATABASE_PASSWORD=<password>
JWT_SECRET=<secret>
```

### Run with Maven

#### Linux / macOS

```bash
./mvnw spring-boot:run
```

#### Windows

```cmd
mvnw.cmd spring-boot:run
```

The application will start at:

```text
http://localhost:8080
```

## Run with Docker

### Pull the Image

```bash
docker pull rishhftw/fitness-tracker-api:latest
```

### Run the Application

```bash
docker run -p 8080:8080 \
  -e DATABASE_URL="jdbc:postgresql://<host>:5432/<database>" \
  -e DATABASE_USERNAME="<username>" \
  -e DATABASE_PASSWORD="<password>" \
  -e JWT_SECRET="<secret>" \
  rishhftw/fitness-tracker-api:latest
```

**[Docker Hub](https://hub.docker.com/r/rishhftw/fitness-tracker-api)**

## Deployment

The application is deployed using:

* **Render** — application hosting
* **Neon** — cloud-hosted PostgreSQL
* **Docker** — containerization

### Live API

**[Open Live API](https://fitness-tracker-api-j1i5.onrender.com/)**

## Project Structure

```text
src/
└── main/
    └── java/
        └── ...
            ├── controller/
            ├── service/
            ├── repository/
            ├── entity/
            ├── dto/
            ├── security/
            └── exception/
```

The project follows a layered architecture separating:

* **Controllers** — Handle HTTP requests and responses
* **Services** — Contain application and business logic
* **Repositories** — Handle database access
* **Entities** — Represent database models
* **DTOs** — Define API request and response structures
* **Security** — Handles authentication and authorization
* **Exception** — Provides centralized error handling

## Validation & Error Handling

The API uses request validation and centralized exception handling to provide consistent responses for invalid requests and application errors.

* Request validation using Jakarta Bean Validation
* Centralized exception handling
* Consistent error responses
* Validation of incoming request data

## Future Improvements

Potential improvements for future versions include:

* Add automated testing
* Add OAuth 2.0 support
* Improve recommendation logic
* Add pagination and filtering for activities
* Add refresh token support
* Introduce caching where appropriate
* Add monitoring and logging
