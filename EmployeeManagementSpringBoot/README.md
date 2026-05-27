# Employee Management System - Spring Boot

Complete CRUD application for managing employees using Java Spring Boot and MySQL.

## Project Structure

```
├── src/main/java/com/employee/
│   ├── EmployeeManagementApplication.java  (Main entry point)
│   ├── model/
│   │   └── Employee.java                    (Entity model)
│   ├── repository/
│   │   └── EmployeeRepository.java         (Data access layer)
│   ├── service/
│   │   └── EmployeeService.java            (Business logic)
│   └── controller/
│       └── EmployeeController.java         (Request handler)
├── src/main/resources/
│   ├── application.properties               (Configuration)
│   └── templates/
│       └── employees/
│           ├── index.html                   (List employees)
│           ├── create.html                  (Add employee form)
│           └── edit.html                    (Edit employee form)
├── database/
│   └── employee_management.sql             (SQL schema)
└── pom.xml                                  (Maven dependencies)
```

## Prerequisites

- Java 17 or higher
- Maven 3.6+
- MySQL 8.0+

## Setup Instructions

### 1. Create Database
```sql
CREATE DATABASE employee_management;
USE employee_management;

-- Import from employee_management.sql
```

### 2. Configure Database Connection

Edit `src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/employee_management
spring.datasource.username=root
spring.datasource.password=YOUR_PASSWORD
```

### 3. Build and Run

```bash
# Navigate to project directory
cd EmployeeManagementSpringBoot

# Build the project
mvn clean install

# Run the application
mvn spring-boot:run
```

Application will run on `http://localhost:8080/employees`

## Features

- **View Employees**: List all employees with pagination and sorting
- **Add Employee**: Form validation with proper error messages
- **Edit Employee**: Update employee information
- **Delete Employee**: Remove employee with confirmation
- **Validation**: 
  - Full name required
  - Email required and unique
  - Phone required
  - Department required
  - Salary numeric

## Technologies Used

- Spring Boot 3.3.0
- Spring Data JPA
- MySQL 8.0
- Thymeleaf (Template Engine)
- Bootstrap 5.3.2
- Lombok
- Jakarta Validation

## Database Schema

```sql
CREATE TABLE `employees` (
  `id` BIGINT AUTO_INCREMENT PRIMARY KEY,
  `full_name` VARCHAR(255) NOT NULL,
  `email` VARCHAR(255) NOT NULL UNIQUE,
  `phone` VARCHAR(255) NOT NULL,
  `department` VARCHAR(255) NOT NULL,
  `salary` DECIMAL(12,2),
  `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  `updated_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
```

## API Endpoints

| Method | Endpoint | Action |
|--------|----------|--------|
| GET | /employees | List all employees |
| GET | /employees/create | Show add form |
| POST | /employees | Save new employee |
| GET | /employees/edit/{id} | Show edit form |
| POST | /employees/update/{id} | Update employee |
| GET | /employees/delete/{id} | Delete employee |

## Validation Rules

- **Full Name**: Required, max 255 characters
- **Email**: Required, unique, valid email format
- **Phone**: Required, max 255 characters
- **Department**: Required, max 255 characters
- **Salary**: Optional, must be numeric if provided

## Author

Created for Employee Management System assessment.
