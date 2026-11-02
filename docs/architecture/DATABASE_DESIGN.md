# Taskyard — Database Design

**Status:** Initial Draft  
**Version:** 1.0  
**Database:** PostgreSQL

## 1. Introduction

This document describes the initial relational database design for Taskyard.

The database will use PostgreSQL to store user accounts, workspaces, memberships, projects, and tasks.

The design aims to maintain data integrity, enforce relationships, and support the application's core workflows.

## 2. Database Tables

### 2.1 users

Stores registered user accounts.

| Column | Type | Constraints |
|---|---|---|
| id | UUID | Primary Key |
| name | VARCHAR(100) | NOT NULL |
| email | VARCHAR(255) | NOT NULL, UNIQUE |
| password_hash | VARCHAR(255) | NOT NULL |
| created_at | TIMESTAMP WITH TIME ZONE | NOT NULL |
| updated_at | TIMESTAMP WITH TIME ZONE | NOT NULL |

### 2.2 workspaces

Stores workspaces created by users.

| Column | Type | Constraints |
|---|---|---|
| id | UUID | Primary Key |
| name | VARCHAR(100) | NOT NULL |
| created_at | TIMESTAMP WITH TIME ZONE | NOT NULL |
| updated_at | TIMESTAMP WITH TIME ZONE | NOT NULL |

Workspace ownership will be represented by a Membership record with the OWNER role rather than duplicating ownership information in the workspaces table.

### 2.3 memberships

Connects users to workspaces and defines their roles.

| Column | Type | Constraints |
|---|---|---|
| id | UUID | Primary Key |
| user_id | UUID | NOT NULL, Foreign Key → users.id |
| workspace_id | UUID | NOT NULL, Foreign Key → workspaces.id |
| role | VARCHAR(20) | NOT NULL |
| joined_at | TIMESTAMP WITH TIME ZONE | NOT NULL |

Additional constraints:

- A user can only have one membership per workspace.
- A unique constraint must exist on `(user_id, workspace_id)`.
- Role must be either OWNER or MEMBER.

### 2.4 projects

Stores projects belonging to workspaces.

| Column | Type | Constraints |
|---|---|---|
| id | UUID | Primary Key |
| workspace_id | UUID | NOT NULL, Foreign Key → workspaces.id |
| name | VARCHAR(150) | NOT NULL |
| description | TEXT | NULL |
| created_at | TIMESTAMP WITH TIME ZONE | NOT NULL |
| updated_at | TIMESTAMP WITH TIME ZONE | NOT NULL |

### 2.5 tasks

Stores individual tasks belonging to projects.

| Column | Type | Constraints |
|---|---|---|
| id | UUID | Primary Key |
| project_id | UUID | NOT NULL, Foreign Key → projects.id |
| assignee_id | UUID | NULL, Foreign Key → users.id |
| title | VARCHAR(200) | NOT NULL |
| description | TEXT | NULL |
| status | VARCHAR(30) | NOT NULL |
| priority | VARCHAR(20) | NOT NULL |
| due_date | DATE | NULL |
| created_at | TIMESTAMP WITH TIME ZONE | NOT NULL |
| updated_at | TIMESTAMP WITH TIME ZONE | NOT NULL |

Allowed status values:

- TODO
- IN_PROGRESS
- DONE

Allowed priority values:

- LOW
- MEDIUM
- HIGH

An unassigned task will have a NULL assignee_id.

## 3. Entity Relationships

### Users and Memberships

One user can have multiple memberships.

Each membership belongs to one user.

Relationship: One-to-many.

### Workspaces and Memberships

One workspace can have multiple memberships.

Each membership belongs to one workspace.

Relationship: One-to-many.

### Workspaces and Projects

One workspace can contain multiple projects.

Each project belongs to one workspace.

Relationship: One-to-many.

### Projects and Tasks

One project can contain multiple tasks.

Each task belongs to one project.

Relationship: One-to-many.

### Users and Tasks

One user can be assigned multiple tasks.

A task can have zero or one assigned user.

Relationship: One-to-many, with an optional task assignee.

## 4. Referential Integrity

The database must enforce the following relationships:

- Every membership must reference an existing user.
- Every membership must reference an existing workspace.
- Every project must reference an existing workspace.
- Every task must reference an existing project.
- Every non-null task assignee must reference an existing user.

The application must additionally enforce that an assigned user belongs to the workspace containing the task's project.

This cross-table business rule requires more than a simple foreign key to users.id. We will evaluate an appropriate database constraint design when implementing the schema.

## 5. Indexing Considerations

The following indexes should be considered:

| Table | Column(s) | Reason |
|---|---|---|
| users | email | Efficient user lookup during authentication |
| memberships | user_id | Retrieve workspaces belonging to a user |
| memberships | workspace_id | Retrieve members belonging to a workspace |
| projects | workspace_id | Retrieve projects belonging to a workspace |
| tasks | project_id | Retrieve tasks belonging to a project |
| tasks | assignee_id | Retrieve tasks assigned to a user |
| tasks | status | Support status-based filtering |

The unique constraint on `(user_id, workspace_id)` will also create an appropriate unique index in PostgreSQL.

Additional indexes should be introduced based on actual query patterns and execution plans rather than added indiscriminately.

## 6. Data Integrity and Deletion Policies

The application must define appropriate deletion behavior for related records.

Initial proposed policies:

- A user with active workspace ownership cannot be deleted without first transferring ownership or deleting the workspace.
- Removing a membership must revoke the user's access to that workspace.
- Deleting a workspace should remove or otherwise account for its projects and tasks.
- Deleting a project should remove or otherwise account for its tasks.
- Deleting a user should not leave invalid task assignments.

The exact use of CASCADE, RESTRICT, SET NULL, or application-managed deletion must be decided during implementation.

## 7. Database Design Principles

The database should follow these principles:

- Use primary keys to uniquely identify records.
- Use foreign keys to enforce referential integrity.
- Use unique constraints to prevent duplicate records.
- Use NOT NULL constraints for required fields.
- Use appropriate data types.
- Avoid storing duplicated information unnecessarily.
- Keep relationships normalized where practical.
- Use database transactions for operations involving multiple related changes.

## 8. Future Database Extensions

Future features may introduce additional tables:

- comments
- activity_logs
- notifications
- attachments
- invitations

These tables are not required for the initial MVP.