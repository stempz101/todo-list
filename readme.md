# Todo List Application

This is a simple todo list application built with Spring Boot, Hibernate, and MySQL. It allows users to create, read, update, and delete todo items, each of which has a title and a description.

## Features

- Create new todo items with a title and description.
- Retrieve a list of all todo items.
- Retrieve a single todo item by its ID.
- Update the title and description of an existing todo item.
- Delete a todo item by its ID.

## Technologies Used

- Spring Boot
- Hibernate (JPA)
- MySQL Database

## Prerequisites

Before running the application, make sure you have the following installed on your system:

- Java Development Kit (JDK)
- MySQL Database
- Integrated Development Environment (IDE) like IntelliJ IDEA or Eclipse (optional)

## Getting Started

1. **Clone this repository to your local machine:**

```bash
git clone https://github.com/stempz101/todo-list.git
```

2. **Configure the Database:**

- Create a MySQL database named `todo_db`.
- Update the database connection properties in the `application.properties` file located in the `src/main/resources` directory with your MySQL username and password.

3. **Build and Run the Application:**

- Open a terminal and navigate to the project's root directory.
- Build the application using Maven:

```bash
mvn clean install
```

- Run the application:
```bash
java -jar target/todo-list-0.0.1-SNAPSHOT.jar
```

4. The application should now be running locally on `http://localhost:8080`.

## Feedback

- Was it easy to complete the task using `AI`?

**Answer:** The task was simple, so it was easy using `AI`.

- How long did task take you to complete?

**Answer:** About 30 minutes to set up and implement the project.

- Was the code ready to run after generation? What did you have to change to make it usable?

**Answer:** Changed only the way of injecting the repository, and added constructor and getters/setters to the entity. After that, it was ready to run.

- Which challenges did you face during completion of the task?

**Answer:** The task is simple, so there wasn't any challenges.

- Which specific prompts you learned as a good practice to complete the task?

Learned that I should use `'''` when pasting the code.
