# Library Management System
This is a Library Management System built with Spring Boot. The project is designed to manage library operations such as borrowing books, managing users, and sending notifications to users who have books due for return. It uses a microservices architecture for scalability and maintainability.

## Features
-**User Management**: Register users, assign roles, and manage user data.

-**Book Management**: Add, update, and delete books in the library.

-**Borrow and Return Books**: Users can borrow books, extend the borrowing period, and return books.

-**Notifications**: Sends notifications to users who have books due for return.

--**Job Scheduling**: A job service runs daily at 08:30 AM to trigger notification emails to users with books due that day.

-**Gateway Service**: Routes requests to appropriate microservices.

## Technologies
-**Backend**: Java, Spring Boot, Spring Security

-**Database**: PostgreSQL

