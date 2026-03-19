# 🎓 Student Management System (JDBC + PostgreSQL)

## 📌 Overview
A Java-based console application to manage student records using JDBC and PostgreSQL.

## 🚀 Features
- Add student
- Retrieve student details
- Update records
- Delete student

## 🛠 Tech Stack
- Java
- JDBC
- PostgreSQL


## ⚙️ Setup Instructions

1. Install PostgreSQL
2. Create database and table:

```sql
CREATE TABLE student (
  id SERIAL PRIMARY KEY,
  name VARCHAR(50),
  email VARCHAR(50),
  phone VARCHAR(15),
  course VARCHAR(50),
  age INT
);

## 3.Create DBConfig.java:

public class DBConfig {
    public static String url = "jdbc:postgresql://localhost:5432/yourdb";
    public static String user = "postgres";
    public static String pass = "yourpassword";
}
