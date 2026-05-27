# Employee Management System - Spring Boot Setup Guide

## ✅ Project Created Successfully!

### Location
```
c:\Users\DELL\Desktop\EmployeeManagementSpringBoot
```

### Project Structure
```
EmployeeManagementSpringBoot/
├── src/main/java/com/employee/
│   ├── EmployeeManagementApplication.java   (Main class)
│   ├── model/Employee.java                   (Entity)
│   ├── repository/EmployeeRepository.java    (Data Access)
│   ├── service/EmployeeService.java          (Business Logic)
│   └── controller/EmployeeController.java    (Request Handler)
├── src/main/resources/
│   ├── application.properties                (DB Config)
│   └── templates/employees/
│       ├── index.html                        (List)
│       ├── create.html                       (Add Form)
│       └── edit.html                         (Edit Form)
├── database/
│   └── employee_management.sql               (SQL Schema)
├── pom.xml                                   (Maven Config)
└── README.md
```

---

## 🔧 Setup Steps

### Step 1: Create Database & Table

Open Command Prompt and run:

```cmd
mysql -u root -p
```

Then enter your MySQL password and run these SQL commands:

```sql
CREATE DATABASE employee_management;
USE employee_management;

CREATE TABLE employees (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  full_name VARCHAR(255) NOT NULL,
  email VARCHAR(255) NOT NULL UNIQUE,
  phone VARCHAR(255) NOT NULL,
  department VARCHAR(255) NOT NULL,
  salary DECIMAL(12,2),
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

EXIT;
```

### Step 2: Update Database Password (if required)

Edit: `src/main/resources/application.properties`

If your MySQL root password is not empty, update:
```properties
spring.datasource.password=YOUR_PASSWORD
```

### Step 3: Build the Project

Open Command Prompt and navigate to the project:

```cmd
cd c:\Users\DELL\Desktop\EmployeeManagementSpringBoot
mvn clean install
```

This will download dependencies and build the application. Wait for completion.

### Step 4: Run the Application

```cmd
mvn spring-boot:run
```

Or run the JAR directly after building:

```cmd
java -jar target/employee-management-1.0.0.jar
```

### Step 5: Access the Application

Open your browser and navigate to:

```
http://localhost:8080/employees
```

---

## 📋 Database Fields

| Field | Type | Validation |
|-------|------|-----------|
| full_name | VARCHAR(255) | Required |
| email | VARCHAR(255) | Required, Unique |
| phone | VARCHAR(255) | Required |
| department | VARCHAR(255) | Required |
| salary | DECIMAL(12,2) | Optional, Numeric |
| created_at | TIMESTAMP | Auto-set |
| updated_at | TIMESTAMP | Auto-updated |

---

## 🎯 CRUD Features

✅ **View List**: GET `/employees`
✅ **Add Employee**: GET/POST `/employees/create`
✅ **Edit Employee**: GET/POST `/employees/edit/{id}`, `/employees/update/{id}`
✅ **Delete Employee**: GET `/employees/delete/{id}`

---

## 🛠 Technologies

- **Framework**: Spring Boot 3.3.0
- **ORM**: Spring Data JPA (Hibernate)
- **Database**: MySQL 8.0+
- **Template Engine**: Thymeleaf
- **Frontend**: Bootstrap 5.3.2
- **Build Tool**: Maven
- **Java Version**: 17+

---

## ✨ Validation Rules

✅ Full Name - Required, max 255 characters
✅ Email - Required, unique, valid format
✅ Phone - Required, max 255 characters
✅ Department - Required, max 255 characters
✅ Salary - Optional, numeric only

---

## 🚀 Example Data

```sql
INSERT INTO employees (full_name, email, phone, department, salary) VALUES
('John Doe', 'john@example.com', '9876543210', 'IT', 50000),
('Jane Smith', 'jane@example.com', '9876543211', 'HR', 45000),
('Bob Johnson', 'bob@example.com', '9876543212', 'Finance', 55000);
```

---

## ❓ Troubleshooting

**Port 8080 already in use?**
Change in `application.properties`:
```properties
server.port=8081
```

**Database connection error?**
Check `application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/employee_management
spring.datasource.username=root
spring.datasource.password=
```

**Maven build slow?**
First run might take longer as it downloads all dependencies. Subsequent builds will be faster.

---

## 📦 Submission Package

Include these files when submitting:
- ✅ Complete `EmployeeManagementSpringBoot` folder
- ✅ `database/employee_management.sql`
- ✅ `pom.xml`
- ✅ `README.md`
- ✅ All source files under `src/`
- ✅ All templates under `resources/templates/`

Application should run on `http://localhost:8080/employees`

---

**Project created successfully! ✨**
