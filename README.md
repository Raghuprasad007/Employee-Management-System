# Employee Management System

A complete backend application built using **Spring Boot**, **Spring Security**, and **PostgreSQL**.  
It provides secure, role-based CRUD operations for managing employees and departments.

---

## 🚀 Features

### 🔐 Role-Based Access Control
- Roles: `ADMIN`, `HR`, `USER`
- Only ADMIN & HR can add/update data
- Only ADMIN can delete
- All authenticated users can view data

### 🧑‍💼 Employee & Department Management
- CRUD API endpoints
- Many-to-One relationship
- Duplicate validation (email & department name)

### ✔ Validation & Global Exception Handling
- DTO validation using `jakarta.validation`
- Centralized exception handling with `@RestControllerAdvice`
- Consistent JSON error responses

### 🗄 Database & ORM
- Spring Data JPA
- PostgreSQL
- Automatic schema management

---

## 🛠 **Tech Stack**
- Java 17  
- Spring Boot 3  
- Spring Web  
- Spring Security  
- Spring Data JPA  
- PostgreSQL  
- Maven  

---

## ⚙️ **How to Run**

### 1️⃣ Create PostgreSQL Database
```sql
CREATE DATABASE ems_db;
CREATE USER ems_user WITH ENCRYPTED PASSWORD 'ems_password';
GRANT ALL PRIVILEGES ON DATABASE ems_db TO ems_user;
