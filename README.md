# Habit Tracker (Backend)

A Spring Boot backend for tracking habits, built with Java and MySQL.

## Prerequisites

- Java 17+ (or whatever JDK version this project targets)
- Maven (or the Maven wrapper included in the repo)
- MySQL Server running locally (or accessible remotely)

## Getting Started

### 1. Clone the repository

```bash
git clone https://github.com/shopFloww/habit-tracker-be.git
cd habit-tracker-be
```

### 2. Create your local environment config

This project uses a Spring profile (`dev`) to keep local database credentials out of version control. The base `application.properties` file references placeholders like `${db.username}` that must be supplied by a local, gitignored `application-dev.properties` file.

Create the file:

```bash
touch src/main/resources/application-dev.properties
```

Then add your local database credentials to it:

```properties
db.url=jdbc:mysql://localhost:3306/habitTracker_db?createDatabaseIfNotExist=true
db.username=your-mysql-username
db.password=your-mysql-password
```

> ⚠️ **Do not commit this file.** It's already covered by the `.gitignore` pattern `application-*.properties`. Never paste real credentials into `application.properties` or any tracked file.

### 3. Make sure MySQL is running

Confirm your local MySQL server is up and that the user in `application-dev.properties` has permission to create/access the `habitTracker_db` database (the app will auto-create it on first run thanks to `createDatabaseIfNotExist=true`).

### 4. Run the application

## Project Structure

```
src/main/java/africa/semicolon/habitTracker/
├── model/
├── repository/
├── service/
└── HabitTrackerApplication.java
```

## Notes

- `spring.jpa.hibernate.ddl-auto=create-drop` is set for local development — this drops and recreates the schema on every run/shutdown. Do **not** carry this setting into a production profile.
- If you see a `Repository not found` error while pushing, check that you're authenticated with a GitHub account that has access to this (private) repo — a Personal Access Token or SSH key is required for HTTPS/SSH pushes.