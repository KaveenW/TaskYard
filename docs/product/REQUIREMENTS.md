# Taskyard — System Requirements

**Status:** Initial Draft  
**Version:** 1.0

## 1. Introduction

This document defines the functional and non-functional requirements of Taskyard.

Functional requirements describe the operations and capabilities the system must provide.

Non-functional requirements describe the quality attributes, constraints, and expectations governing how the system operates.

## 2. Functional Requirements

### 2.1 User Authentication

| ID | Requirement | Priority |
|---|---|---|
| FR-001 | The system shall allow users to register using their name, email address, and password. | Must |
| FR-002 | The system shall prevent registration using an email address already associated with an account. | Must |
| FR-003 | The system shall allow registered users to log in. | Must |
| FR-004 | The system shall allow authenticated users to log out. | Must |
| FR-005 | The system shall protect restricted operations from unauthenticated access. | Must |

### 2.2 Workspace Management

| ID | Requirement | Priority |
|---|---|---|
| FR-006 | The system shall allow authenticated users to create workspaces. | Must |
| FR-007 | The system shall automatically assign the workspace creator the OWNER role. | Must |
| FR-008 | The system shall allow users to view workspaces they belong to. | Must |
| FR-009 | The system shall allow workspace owners to add existing registered users to their workspace. | Must |
| FR-010 | The system shall support OWNER and MEMBER workspace roles. | Must |
| FR-011 | The system shall allow workspace owners to remove members, subject to ownership rules. | Must |

### 2.3 Project Management

| ID | Requirement | Priority |
|---|---|---|
| FR-012 | The system shall allow authorized workspace members to create projects. | Must |
| FR-013 | The system shall allow workspace members to view projects belonging to their workspace. | Must |
| FR-014 | The system shall allow authorized members to update project information. | Must |
| FR-015 | The system shall allow authorized members to delete projects. | Must |

### 2.4 Task Management

| ID | Requirement | Priority |
|---|---|---|
| FR-016 | The system shall allow authorized users to create tasks within projects. | Must |
| FR-017 | The system shall allow authorized users to view tasks within projects they can access. | Must |
| FR-018 | The system shall allow authorized users to update task details. | Must |
| FR-019 | The system shall allow authorized users to delete tasks. | Must |
| FR-020 | The system shall allow tasks to be assigned to workspace members. | Must |
| FR-021 | The system shall support TODO, IN_PROGRESS, and DONE task statuses. | Must |
| FR-022 | The system shall support LOW, MEDIUM, and HIGH task priorities. | Must |
| FR-023 | The system shall allow users to set an optional task due date. | Must |

### 2.5 Task Organization

| ID | Requirement | Priority |
|---|---|---|
| FR-024 | The system shall allow tasks to be filtered by status. | Should |
| FR-025 | The system shall allow tasks to be filtered by priority. | Should |
| FR-026 | The system shall allow tasks to be filtered by assignee. | Should |
| FR-027 | The system shall allow tasks to be sorted by creation date and due date. | Should |

## 3. Non-Functional Requirements

### 3.1 Security

| ID | Requirement |
|---|---|
| NFR-001 | User passwords must never be stored in plaintext. |
| NFR-002 | Protected API endpoints must enforce authentication. |
| NFR-003 | The backend must enforce authorization for workspace, project, and task operations. |
| NFR-004 | Users must not be able to access resources belonging to workspaces they are not authorized to access. |
| NFR-005 | Sensitive configuration and credentials must not be committed to source control. |

### 3.2 Performance

| ID | Requirement |
|---|---|
| NFR-006 | Common API operations should respond efficiently under normal application usage. |
| NFR-007 | Database queries should retrieve only the data required for each operation. |
| NFR-008 | Task listing endpoints should support pagination as the application grows. |

### 3.3 Maintainability

| ID | Requirement |
|---|---|
| NFR-009 | Backend code should maintain clear separation between API, business logic, and persistence responsibilities. |
| NFR-010 | The application should use consistent naming conventions and coding standards. |
| NFR-011 | Business logic should be designed to support automated testing. |

### 3.4 Reliability and Data Integrity

| ID | Requirement |
|---|---|
| NFR-012 | Invalid operations must return consistent and meaningful error responses. |
| NFR-013 | Database relationships and constraints must prevent invalid references. |
| NFR-014 | Operations involving multiple related database changes should maintain transactional consistency where required. |

### 3.5 Testing

| ID | Requirement |
|---|---|
| NFR-015 | Core business logic must have automated tests. |
| NFR-016 | Important API workflows must be tested, including successful and unsuccessful operations. |
| NFR-017 | Authorization rules must be covered by automated tests. |

### 3.6 Usability

| ID | Requirement |
|---|---|
| NFR-018 | The user interface should provide clear navigation between workspaces, projects, and tasks. |
| NFR-019 | The application should provide understandable feedback when an operation succeeds or fails. |

## 4. Requirement Priority Definitions

- **Must:** Essential for the initial MVP.
- **Should:** Important, but can be completed after the core functionality.
- **Could:** Useful improvement that is not essential to the initial release.

## 5. Assumptions and Constraints

- Taskyard will initially be a web application.
- The backend will use Java and Spring Boot.
- PostgreSQL will be used for persistent data storage.
- React will be used for the frontend.
- Users must register before accessing protected functionality.
- Workspace membership will determine access to workspace resources.
- The initial version will support basic OWNER and MEMBER roles.
- Advanced real-time and asynchronous functionality is excluded from the MVP.