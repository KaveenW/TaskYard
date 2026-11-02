# Architectural Decision Records (ADRs)

## 1. Purpose

Architectural Decision Records (ADRs) document important technical decisions made during the development of Taskyard.

They provide a historical record of:

- The problem or context behind a decision.
- The alternatives considered.
- The selected approach.
- The reasons for selecting that approach.
- The consequences of the decision.

ADRs help developers understand why the system was designed in a particular way.

## 2. ADR Naming Convention

ADRs will use sequential numbering.

Format:

`NNNN-short-decision-title.md`

Examples:

- `0001-use-postgresql.md`
- `0002-use-modular-monolith.md`
- `0003-use-spring-security.md`

## 3. ADR Template

New ADRs should follow this structure:

# ADR-NNNN: Decision Title

**Status:** Proposed / Accepted / Superseded / Deprecated

**Date:** YYYY-MM-DD

## Context

Describe the problem, requirement, or situation that requires a decision.

## Decision

Describe the selected approach.

## Alternatives Considered

List the alternatives and briefly explain their advantages and disadvantages.

## Consequences

Describe the positive and negative consequences of the decision.

## References

Include relevant documentation or technical references where appropriate.

## 4. Initial Architectural Decisions

The following are the initial technology and architecture choices for Taskyard:

| Decision | Selected Approach |
|---|---|
| Backend language | Java 21 |
| Backend framework | Spring Boot |
| Database | PostgreSQL |
| Frontend | React |
| Architecture | Modular monolith |
| API communication | REST over HTTP |
| Persistence | Spring Data JPA and Hibernate |
| Database migrations | Flyway |
| Version control | Git and GitHub |

Formal ADRs will be created for decisions where documenting the reasoning is valuable.