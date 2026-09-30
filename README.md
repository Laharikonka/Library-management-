# Library Management System

A beginner-friendly Library Management System built with **Java 17, Spring Boot, Spring Data JPA, Thymeleaf and MySQL**.

## Features
- Dashboard
- Add, edit, delete and search books
- Add, edit and delete members
- Issue books to members
- Return books
- Track issue/return dates
- MySQL database persistence
- REST APIs for books, members and transactions
- Responsive browser interface
- GitHub-ready Maven project

## Requirements
- Java 17+
- Maven 3.9+
- MySQL 8+
- IntelliJ IDEA / Eclipse / VS Code (optional)

## 1. Create the database
Open MySQL and run:

```sql
SOURCE database/library_management.sql;
```

Or copy the SQL from `database/library_management.sql` into MySQL Workbench.

## 2. Configure database
Edit:
`src/main/resources/application.properties`

Change these values if required:

```properties
spring.datasource.username=root
spring.datasource.password=YOUR_MYSQL_PASSWORD
```

The default database name is `library_management`.

## 3. Run
From the project folder:

```bash
mvn clean spring-boot:run
```

Open:
http://localhost:8080

## Default sample data
The SQL script includes sample books and members. You can remove them if you want an empty database.

## REST API endpoints
- GET/POST/PUT/DELETE `/api/books`
- GET/POST/PUT/DELETE `/api/members`
- GET/POST `/api/transactions`
- PUT `/api/transactions/{id}/return`
- GET `/api/dashboard`

## GitHub
Do not commit real database passwords. For a public repository, keep credentials out of source control and use environment variables or a local properties file.

## Project structure
```text
LibraryManagementSystem/
├── database/
├── src/main/java/com/library/
│   ├── LibraryManagementApplication.java
│   ├── controller/
│   ├── model/
│   ├── repository/
│   └── service/
├── src/main/resources/
│   ├── static/css/
│   ├── templates/
│   └── application.properties
├── .gitignore
├── pom.xml
└── README.md
```
