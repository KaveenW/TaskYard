# Taskyard — Domain Model

**Status:** Initial Draft  
**Version:** 1.0

## 1. Introduction

This document describes the core domain entities, their responsibilities, relationships, and business rules within Taskyard.

The domain model represents the business concepts of the application independently of specific database or framework implementation details.

## 2. Core Domain Entities

### 2.1 User

Represents an individual registered account on Taskyard.

Responsibilities:

- Store user account information.
- Authenticate with the application.
- Create workspaces.
- Belong to multiple workspaces.
- Create and manage projects and tasks according to permissions.

Initial attributes:

| Attribute | Description |
|---|---|
| id | Unique identifier |
| name | User's display name |
| email | Unique email address |
| password_hash | Securely hashed password |
| created_at | Account creation timestamp |
| updated_at | Last update timestamp |

### 2.2 Workspace

Represents a shared environment where users organize projects and collaborate.

Responsibilities:

- Group users through memberships.
- Contain projects.
- Define the collaboration boundary for its members.
- Have one or more owners.

Initial attributes:

| Attribute | Description |
|---|---|
| id | Unique identifier |
| name | Workspace name |
| created_at | Workspace creation timestamp |
| updated_at | Last update timestamp |

Workspace ownership is represented through a Membership record with the OWNER role.

### 2.3 Membership

Represents the relationship between a User and a Workspace.

Membership is important because it connects users to workspaces and stores their workspace-specific permissions.

Responsibilities:

- Associate a user with a workspace.
- Define the user's role within that workspace.
- Record when the user joined.

Initial attributes:

| Attribute | Description |
|---|---|
| id | Unique identifier |
| user_id | Reference to the associated user |
| workspace_id | Reference to the associated workspace |
| role | OWNER or MEMBER |
| joined_at | Membership creation timestamp |

### 2.4 Project

Represents a collection of related tasks within a workspace.

Responsibilities:

- Organize tasks.
- Belong to exactly one workspace.
- Provide project-level information.

Initial attributes:

| Attribute | Description |
|---|---|
| id | Unique identifier |
| workspace_id | Reference to the owning workspace |
| name | Project name |
| description | Optional project description |
| created_at | Project creation timestamp |
| updated_at | Last update timestamp |

### 2.5 Task

Represents an individual piece of work within a project.

Responsibilities:

- Describe work that needs to be completed.
- Belong to one project.
- Track progress and priority.
- Optionally identify an assigned workspace member.
- Optionally define a due date.

Initial attributes:

| Attribute | Description |
|---|---|
| id | Unique identifier |
| project_id | Reference to the owning project |
| assignee_id | Optional reference to the assigned user |
| title | Task title |
| description | Optional task description |
| status | TODO, IN_PROGRESS, or DONE |
| priority | LOW, MEDIUM, or HIGH |
| due_date | Optional due date |
| created_at | Task creation timestamp |
| updated_at | Last update timestamp |

## 3. Entity Relationships

### 3.1 User and Workspace

Relationship: Many-to-many through Membership.

- A user can belong to multiple workspaces.
- A workspace can have multiple users.
- Membership records represent each association.
- Each membership has a role.

### 3.2 Workspace and Project

Relationship: One-to-many.

- A workspace can contain multiple projects.
- Each project belongs to exactly one workspace.

### 3.3 Project and Task

Relationship: One-to-many.

- A project can contain multiple tasks.
- Each task belongs to exactly one project.

### 3.4 User and Task

Relationship: One-to-many for assignment.

- A user can be assigned multiple tasks.
- A task can have zero or one assigned user.
- An assigned user must be a member of the workspace containing the task's project.

### 3.5 User and Membership

Relationship: One-to-many.

- A user can have multiple membership records.
- Each membership belongs to exactly one user.

### 3.6 Workspace and Membership

Relationship: One-to-many.

- A workspace can have multiple membership records.
- Each membership belongs to exactly one workspace.

## 4. Business Rules

| ID | Business Rule |
|---|---|
| BR-001 | Every workspace must have at least one OWNER. |
| BR-002 | A user cannot have duplicate memberships in the same workspace. |
| BR-003 | The user creating a workspace automatically becomes its OWNER. |
| BR-004 | A workspace can contain multiple projects. |
| BR-005 | Every project must belong to exactly one workspace. |
| BR-006 | Every task must belong to exactly one project. |
| BR-007 | A task can be assigned to zero or one user. |
| BR-008 | A task's assignee must belong to the workspace containing its project. |
| BR-009 | Only authorized workspace members can access workspace resources. |
| BR-010 | Only workspace OWNERS can manage workspace membership. |
| BR-011 | The system must prevent removal of the last workspace OWNER. |
| BR-012 | Task status must be TODO, IN_PROGRESS, or DONE. |
| BR-013 | Task priority must be LOW, MEDIUM, or HIGH. |
| BR-014 | A task may have a null due date. |
| BR-015 | Users must not access resources belonging to workspaces they are not authorized to access. |

## 5. Authorization Model

Taskyard will initially use role-based authorization at the workspace level.

### OWNER

An OWNER can:

- View workspace information.
- Manage workspace membership.
- Add and remove members.
- Create, update, and delete projects.
- Manage tasks.
- Perform other authorized workspace operations.

### MEMBER

A MEMBER can:

- View workspace information.
- View workspace projects.
- Create and manage tasks.
- Assign tasks to eligible workspace members.
- Update task status and priority.

Membership permissions must be enforced by the backend.

## 6. Domain Modelling Considerations

The following design considerations must be addressed during implementation:

- Workspace ownership should be represented consistently through membership.
- Membership uniqueness must be enforced.
- Task assignments must respect workspace boundaries.
- Deleting projects must not leave orphaned tasks.
- Removing a member must be handled consistently if they have assigned tasks.
- Deleting a workspace must account for its projects and tasks.
- Database constraints and application-level validation should work together to preserve business rules.

## 7. Future Domain Extensions

Possible future entities include:

- Comment: Discussion associated with a task.
- ActivityLog: Records important actions within a workspace or project.
- Notification: Stores notifications for users.
- Attachment: Represents files associated with tasks.
- Invitation: Represents invitations to join workspaces.

These entities are excluded from the initial MVP.