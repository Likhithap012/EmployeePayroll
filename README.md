# 👩‍💼 Employee Payroll Management System

A **Spring Boot** application to manage employee payroll records using a **RESTful API**. This application uses an in-memory `List` for storing employee data (no database integration) and supports full **CRUD operations** via a layered architecture (Controller → Service → Mapper → DTO → Entity).

---


## 📁 Project Structure

```

com.gevernova.employeerollapp
├── controller              # REST Controllers (EmployeeController)
├── dto                    # Request and Response DTOs (EmployeeDTO)
├── entity                 # Entity classes (Employee, etc.)
├── exceptions             # Global Exception Handling
├── mapper                 # Mapper classes (DTO ↔ Entity)
├── repository             # JPA Repositories (if extended later)
├── service                # Business Logic Layer (Interfaces + Implementations)
├── util                   # Utility classes (e.g., EmailService for notifications)
└── EmployeePayrollApplication.java   # Main Spring Boot app class


```



---

## Features

- ✅ Get all employees  
- ✅ Get employee by ID  
- ✅ Add a new employee  
- ✅ Update employee details by ID  
- ✅ Delete employee by ID  
- ✅ Input validation using `@Valid`  
- ✅ Email notification on employee creation (via `jakarta.mail`)  
- ✅ Clean architecture using DTOs and Mappers  
- ✅ Uses in-memory storage (Java List)

---

## 🛠️ Setup Instructions

### 1️⃣ Clone the Repository

```bash
git clone https://github.com/yourusername/EmployeePayrollApp.git
cd EmployeePayrollApp
````

### 2️⃣ Open and Build in IDE

* Open the project in **IntelliJ IDEA** or **Eclipse**.
* Ensure it's imported as a **Maven** project.
* Build the project and run `EmployeePayrollApplication.java`.

### 3️⃣ Access Application

```bash
App runs at: http://localhost:8080
```

---

## 🔗 API Endpoints

Base URL:
`http://localhost:8080/employeepayrollservice`

| Method | Endpoint       | Description             |
| ------ | -------------- | ----------------------- |
| GET    | `/`            | Retrieve all employees  |
| GET    | `/get/{id}`    | Retrieve employee by ID |
| POST   | `/create`      | Create a new employee   |
| PUT    | `/update/{id}` | Update employee by ID   |
| DELETE | `/delete/{id}` | Delete employee by ID   |

---

##  Sample API Requests

### 🔹 1. Create Employee

**POST** `/create`

**Request Body:**

```json
{
  "name": "Likhitha Pulluru",
  "salary": 50000,
  "email": "likhitha@example.com",
  "departments": ["HR", "Finance"]
}
```

---

### 🔹 2. Get All Employees

**GET** `/`

**Response:**

```json
[
  {
    "id": 1,
    "name": "Likhitha Pulluru",
    "salary": 50000,
    "email": "likhitha@example.com",
    "departments": ["HR", "Finance"]
  }
]
```

---

### 🔹 3. Get Employee by ID

**GET** `/get/{id}`
**Example:** `/get/1`

---

### 🔹 4. Update Employee

**PUT** `/update/{id}`
**Example:** `/update/1`

**Request Body:**

```json
{
  "name": "Updated Name",
  "salary": 60000,
  "email": "newemail@example.com",
  "departments": ["Tech", "QA"]
}
```

---

### 🔹 5. Delete Employee

**DELETE** `/delete/{id}`
**Example:** `/delete/1`

**Response:**

```
Employee with ID 1 has been deleted successfully.
```



