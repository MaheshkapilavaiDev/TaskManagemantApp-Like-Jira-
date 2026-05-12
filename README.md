# Task Management System

Simple Jira-like backend application developed using Spring Boot.

## Technologies Used

- Java 21
- Spring Boot
- Spring Data JPA
- Spring Security
- JWT Authentication
- MySQL
- Swagger OpenAPI
- Maven

## Features

- User Management
- Admin Management
- Project Management
- Task Management
- Assign Tasks to Users
- Task Status Tracking
- Task Priority Management
- Comments and Activity Tracking
- Pagination and Search
- Dashboard APIs
- JWT Authentication and Authorization
- Global Exception Handling
- Swagger API Documentation

## Project Structure

- Controller Layer
- Service Layer
- Repository Layer
- DTO Layer
- Entity Layer
- Security Layer
- Exception Handling Layer

## APIs

Authentication APIs
- POST /auth/register
- POST /auth/login

User APIs
- POST /users
- GET /users
- PUT /users/{id}
- DELETE /users/{id}

Project APIs
- POST /projects
- GET /projects

Task APIs
- POST /tasks
- GET /tasks
- GET /tasks/project/{projectId}
- GET /tasks/user/{userId}
- PUT /tasks/{id}
- DELETE /tasks/{id}

Admin APIs
- POST /admin/create

## Database Configuration

Update application.properties

spring.datasource.url=jdbc:mysql://localhost:3306/task_management
spring.datasource.username=root
spring.datasource.password=root

## Run Application

mvn spring-boot:run

## Swagger Documentation

http://localhost:8080/swagger-ui/index.html

## Default Admin

Email : maheshSuperAdmin@gmail.com
Password : admin123

## Author

Mahesh Kumar