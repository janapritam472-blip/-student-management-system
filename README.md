# 🎓 StudentHub — Student Management System

A full-stack Student Management System built with:
- **Backend**: Java 17 + Spring Boot 3 + Spring Data JPA
- **Database**: H2 (in-memory, dev) / MySQL or PostgreSQL (production)
- **Frontend**: Vanilla HTML + CSS + JavaScript (no frameworks)

---

## 📁 Project Structure

```
student-management/
├── pom.xml                          ← Maven dependencies
├── schema.sql                       ← Reference SQL schema
├── frontend/
│   └── index.html                   ← Single-page frontend app
└── src/main/
    ├── java/com/studentmgmt/
    │   ├── StudentManagementApplication.java
    │   ├── model/Student.java
    │   ├── repository/StudentRepository.java
    │   ├── service/StudentService.java
    │   ├── controller/StudentController.java
    │   └── exception/
    │       ├── StudentNotFoundException.java
    │       ├── DuplicateEmailException.java
    │       └── GlobalExceptionHandler.java
    └── resources/
        ├── application.properties
        ├── schema.sql   ← empty (JPA handles DDL)
        └── data.sql     ← seed data (5 sample students)
```

---

## 🚀 Quick Start

### Prerequisites
- Java 17+
- Maven 3.8+
- (Optional) MySQL 8+ for production

### 1. Run the Backend

```bash
cd student-management
mvn spring-boot:run
```

The API starts at **http://localhost:8080**  
H2 Console available at **http://localhost:8080/h2-console** (JDBC URL: `jdbc:h2:mem:studentdb`)

### 2. Open the Frontend

Open `frontend/index.html` in your browser.  
> Since it calls `http://localhost:8080`, open it directly as a file or serve it locally.

---

## 🔌 REST API Reference

| Method | Endpoint                    | Description              |
|--------|-----------------------------|--------------------------|
| GET    | `/api/students`             | List all students        |
| GET    | `/api/students?q=<query>`   | Search students          |
| GET    | `/api/students/stats`       | Get total/active/inactive counts |
| GET    | `/api/students/{id}`        | Get student by ID        |
| POST   | `/api/students`             | Create new student       |
| PUT    | `/api/students/{id}`        | Update student           |
| DELETE | `/api/students/{id}`        | Delete student           |

### Sample POST Body
```json
{
  "firstName": "Anjali",
  "lastName":  "Singh",
  "email":     "anjali.singh@uni.edu",
  "phone":     "+91-9812345678",
  "course":    "Computer Science",
  "grade":     "A",
  "status":    "Active",
  "enrolledAt": "2024-07-15"
}
```

---

## 🗄️ Switching to MySQL (Production)

1. In `pom.xml`, uncomment the MySQL driver dependency.
2. In `application.properties`, comment out the H2 block and uncomment the MySQL block:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/studentdb?useSSL=false&serverTimezone=UTC
   spring.datasource.username=root
   spring.datasource.password=your_password
   spring.jpa.hibernate.ddl-auto=update
   ```
3. Create the database:
   ```sql
   CREATE DATABASE studentdb CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
   ```
4. Run the seed data from `schema.sql` if needed.

---

## ✨ Features

- **Auto-generated IDs**: `STU-0001`, `STU-0002`, …
- **Full CRUD** via REST API
- **Search**: across name, ID, email, course
- **Validation**: required fields, email format, duplicate email check
- **Statistics**: live total / active / inactive counters
- **Seed data**: 5 sample students on startup
