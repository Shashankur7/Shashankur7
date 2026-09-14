# Employee Leave Management System

A Spring Boot REST API for managing employees and employee leave requests.

> Development project — currently in progress.

## Current Scope

- Create, read, update, and delete employees
- Create and view leave requests
- Find leave requests by employee
- Approve or reject a leave request through its status
- Validate employee and leave request input
- Persist data with Spring Data JPA and MySQL

## Technology Stack

- Java 17
- Spring Boot
- Spring Web
- Spring Data JPA / Hibernate
- MySQL
- Maven
- Bean Validation

## Architecture

```text
Client
  |
  v
REST Controller
  |
  v
Service Layer
  |
  v
JPA Repository
  |
  v
MySQL
```

## API Endpoints

### Employees

| Method | Endpoint | Purpose |
| --- | --- | --- |
| POST | `/api/employees` | Create employee |
| GET | `/api/employees` | List employees |
| GET | `/api/employees/{id}` | Get employee |
| PUT | `/api/employees/{id}` | Update employee |
| DELETE | `/api/employees/{id}` | Delete employee |

### Leave Requests

| Method | Endpoint | Purpose |
| --- | --- | --- |
| POST | `/api/leaves` | Create leave request |
| GET | `/api/leaves` | List leave requests |
| GET | `/api/leaves/{id}` | Get leave request |
| GET | `/api/leaves/employee/{employeeId}` | List employee leave requests |
| PATCH | `/api/leaves/{id}/status?status=APPROVED` | Update leave status |

## Local Setup

1. Install Java 17, Maven, and MySQL.
2. Create a database named `employee_leave_db`.
3. Copy `src/main/resources/application.properties.example` to `application.properties`.
4. Add your local MySQL username and password.
5. Run the application with:

```bash
mvn spring-boot:run
```

The API starts on the default Spring Boot port: `8080`.

## Project Structure

```text
src/main/java/com/shashank/leave
├── controller
├── entity
├── repository
├── service
└── EmployeeLeaveManagementApplication.java
```

## Planned Improvements

- Spring Security authentication and role-based authorization
- Employee and administrator roles
- DTOs and centralized exception handling
- Pagination and filtering
- JUnit and Mockito tests
- Swagger/OpenAPI documentation
- Frontend dashboard
- Deployment with CI/CD

## Status

The project is being developed incrementally as part of a Java full-stack portfolio. Features marked under planned improvements are intentionally not presented as completed.
