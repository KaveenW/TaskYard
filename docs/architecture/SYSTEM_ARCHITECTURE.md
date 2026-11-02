# Taskyard — System Architecture

**Status:** Initial Draft  
**Version:** 1.0

## 1. Introduction

This document describes the initial high-level architecture of Taskyard.

The application will initially follow a modular monolithic architecture.

The backend will be developed using Java and Spring Boot, the frontend using React, and PostgreSQL will be used for persistent storage.

The architecture should prioritize simplicity, maintainability, testability, and a clear separation of responsibilities.

## 2. Architecture Overview

Taskyard will consist of three primary components:

1. Frontend application.
2. Backend REST API.
3. Relational database.

### High-Level Architecture

```text
              USER
                |
                v
        REACT FRONTEND
                |
                | HTTP / JSON
                v
       SPRING BOOT BACKEND
                |
       ---------------------
       |         |         |
       v         v         v
   Security   Services  Controllers
       |         |         |
       |         v         |
       |     Repositories  |
       |         |         |
       --------- | --------
                 |
                 v
             POSTGRESQL
```

The frontend communicates with the backend through HTTP-based REST APIs.

The backend validates requests, authenticates users, enforces business rules, and interacts with PostgreSQL through the persistence layer.

## 3. Technology Stack

### Backend

| Technology | Purpose |
|---|---|
| Java 21 | Primary backend programming language |
| Spring Boot | Backend application framework |
| Spring Web | REST API development |
| Spring Data JPA | Data access and repository abstraction |
| Hibernate | ORM implementation |
| Spring Security | Authentication and authorization |
| Jakarta Validation | Request and data validation |
| Maven | Dependency and build management |

### Database

| Technology | Purpose |
|---|---|
| PostgreSQL | Relational database |
| Flyway | Database schema migrations |

### Frontend

| Technology | Purpose |
|---|---|
| React | User interface |
| JavaScript / TypeScript | Frontend programming |
| CSS | Interface styling |

### Development and Quality

| Technology | Purpose |
|---|---|
| Git | Version control |
| GitHub | Source code hosting and collaboration |
| Docker | Containerization |
| Docker Compose | Local development environment |
| JUnit 5 | Automated testing |
| Mockito | Unit testing with mock dependencies |
| GitHub Actions | Continuous integration |

The initial implementation may introduce additional tools when there is a clear requirement.

## 4. Backend Architecture

The backend will follow a layered architecture.

### 4.1 Controller Layer

Responsibilities:

- Receive HTTP requests.
- Validate incoming request data.
- Delegate operations to service classes.
- Return appropriate HTTP responses.

Controllers should not contain complex business logic.

### 4.2 Service Layer

Responsibilities:

- Implement business operations.
- Enforce business rules.
- Coordinate operations across repositories.
- Manage transactional boundaries where required.
- Apply authorization-related business checks.

This layer contains the primary application logic.

### 4.3 Repository Layer

Responsibilities:

- Perform database operations.
- Retrieve entities.
- Save and update records.
- Support queries required by the service layer.

Spring Data JPA repositories will initially be used for persistence.

### 4.4 Entity Layer

Responsibilities:

- Represent persistent domain data.
- Define entity relationships.
- Map Java objects to relational database tables.

### 4.5 DTO Layer

Data Transfer Objects will be used to control the information entering and leaving the API.

They will help prevent direct exposure of persistence entities and support request validation.

### 4.6 Exception Handling

A centralized exception-handling mechanism will provide consistent API error responses.

The backend should handle:

- Invalid input.
- Missing resources.
- Authentication failures.
- Authorization failures.
- Business rule violations.
- Unexpected server errors.

## 5. Initial Backend Package Structure

The backend may initially follow a feature-oriented package structure:

```text
backend/
└── src/
    └── main/
        └── java/
            └── com/taskyard/
                ├── TaskyardApplication.java
                │
                ├── config/
                │
                ├── security/
                │
                ├── common/
                │   ├── exception/
                │   └── response/
                │
                ├── user/
                │   ├── User.java
                │   ├── UserRepository.java
                │   ├── UserService.java
                │   ├── UserController.java
                │   └── dto/
                │
                ├── workspace/
                │   ├── Workspace.java
                │   ├── Membership.java
                │   ├── WorkspaceRepository.java
                │   ├── WorkspaceService.java
                │   ├── WorkspaceController.java
                │   └── dto/
                │
                ├── project/
                │   ├── Project.java
                │   ├── ProjectRepository.java
                │   ├── ProjectService.java
                │   ├── ProjectController.java
                │   └── dto/
                │
                └── task/
                    ├── Task.java
                    ├── TaskRepository.java
                    ├── TaskService.java
                    ├── TaskController.java
                    └── dto/
```

This is an initial proposal and may be adjusted as the implementation develops.

## 6. API Communication

The frontend will communicate with the backend using RESTful HTTP endpoints.

JSON will be used for request and response bodies.

The API will use appropriate HTTP methods:

- GET for retrieving resources.
- POST for creating resources.
- PUT or PATCH for updating resources.
- DELETE for deleting resources.

The backend will return appropriate HTTP status codes.

## 7. Authentication and Authorization

Taskyard will use Spring Security for authentication and authorization.

An appropriate token-based authentication mechanism will be selected for the initial implementation.

The backend must:

- Authenticate users.
- Protect restricted endpoints.
- Identify the authenticated user.
- Verify workspace membership.
- Enforce OWNER and MEMBER permissions.
- Prevent unauthorized access to projects and tasks.

Authorization checks must be performed on the backend, regardless of frontend visibility.

## 8. Database Access

The backend will use Spring Data JPA and Hibernate to interact with PostgreSQL.

Database schema changes will be managed through Flyway migrations.

Transactional operations will use appropriate transaction boundaries to maintain data consistency.

## 9. Testing Strategy

The application will use multiple testing levels.

### Unit Tests

Test individual units of business logic, particularly service-layer behavior.

### Integration Tests

Test interactions between application components, including repositories and the database.

### API Tests

Verify endpoint behavior, validation, authentication, authorization, and response handling.

The most important business rules and security boundaries must have automated test coverage.

## 10. Development and Deployment

Initially, the application will run locally.

Docker Compose will be used to simplify the local PostgreSQL development environment.

The application should be configurable through environment variables.

GitHub Actions will eventually be used to automate backend builds and tests.

Deployment will be considered after the core MVP is functional and tested.

## 11. Future Architecture Improvements

Potential future improvements include:

- Redis caching.
- WebSocket-based real-time communication.
- Asynchronous background processing.
- RabbitMQ or Kafka integration.
- Centralized logging and monitoring.
- Cloud deployment.
- Automated deployment pipelines.

These technologies will only be introduced when a specific requirement justifies them.

## 12. Architectural Principles

The architecture will follow these principles:

- Prefer simplicity over unnecessary complexity.
- Maintain clear separation of responsibilities.
- Keep business logic out of controllers.
- Enforce security on the backend.
- Use DTOs for API communication.
- Maintain database integrity.
- Write automated tests for important behavior.
- Introduce infrastructure only when it provides meaningful value.