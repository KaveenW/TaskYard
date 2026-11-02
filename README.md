# Taskyard

**A team collaboration and task management platform.**

Taskyard is a full-stack web application designed to help individuals and small teams organize workspaces, manage projects, assign tasks, and track progress through a centralized platform.

The project is being developed using Java, Spring Boot, PostgreSQL, and React, with a focus on backend engineering, REST API development, relational database design, authentication, authorization, and software engineering best practices.

## Features

### MVP Features

- User registration and authentication.
- Workspace creation and management.
- Workspace membership and role management.
- Project creation and management.
- Task creation and management.
- Task assignment.
- Task status and priority tracking.
- Due dates.
- Task filtering and sorting.
- Role-based authorization.

### Planned Future Features

- Task comments.
- Activity history.
- Notifications.
- Redis caching.
- Real-time updates.
- Background processing.
- Reporting and analytics.
- AI-assisted task summaries.

## Technology Stack

### Backend
- Java 21
- Spring Boot
- Spring Security
- Spring Data JPA
- Hibernate
- Maven

### Database
- PostgreSQL
- Flyway

### Frontend
- React
- JavaScript / TypeScript

### Development Tools
- Git
- GitHub
- Docker
- Docker Compose
- JUnit 5
- Mockito
- GitHub Actions

## Architecture

Taskyard will initially follow a modular monolithic architecture.

The backend will expose REST APIs consumed by the React frontend. PostgreSQL will provide persistent data storage.

Further architectural details can be found in the [`docs/architecture`](docs/architecture/) directory.

## Project Structure

```text
taskyard/
├── backend/
├── frontend/
├── docs/
│   ├── product/
│   ├── architecture/
│   └── adr/
├── README.md
└── .gitignore
```

The backend and frontend directories will be added when implementation begins.

## Documentation

| Document | Description |
|---|---|
| [Product Requirements](docs/product/PRD.md) | Product overview, goals and scope |
| [System Requirements](docs/product/REQUIREMENTS.md) | Functional and non-functional requirements |
| [User Stories](docs/product/USER_STORIES.md) | User stories and acceptance criteria |
| [Domain Model](docs/architecture/DOMAIN_MODEL.md) | Domain entities and business rules |
| [Database Design](docs/architecture/DATABASE_DESIGN.md) | Initial relational database design |
| [System Architecture](docs/architecture/SYSTEM_ARCHITECTURE.md) | High-level technical architecture |
| [Architectural Decisions](docs/adr/README.md) | Architectural decision records |

## Project Status

**Current stage:** Initial project documentation and design.

The project will be developed incrementally, starting with the core MVP before introducing advanced functionality.

## Learning Objectives

This project is intended to provide practical experience with:

- Java and object-oriented programming.
- Spring Boot application development.
- REST API design and implementation.
- Relational database modelling.
- SQL and PostgreSQL.
- Authentication and authorization.
- Data validation and exception handling.
- Automated testing.
- Git workflows and version control.
- Docker and containerization.
- Application architecture and maintainability.
- CI/CD and deployment.

## License

No license has been selected yet.