📝 Spring Notes Project

A simple note-taking backend application built with Spring Boot.
Users can register, login with JWT, and manage their notes securely.

🚀 Features

User Authentication

Register new users

Login with JWT token

Notes Management

Add new notes

Update existing notes

Delete notes

Mark notes as completed or uncompleted

Search

Search notes by keyword

Security

Users can only view, update, or delete their own notes

Cannot access notes of other users

DTO Mapping

NoteMapper used to reduce complexity and duplicate code

💻 Tech Stack

Java 17+

Spring Boot

Spring Web

Spring Data JPA

Spring Security

PostgreSQL

JWT for authentication

Lombok

🔑 Usage

Clone the repository

Configure your database in application.properties / .env

Run the Spring Boot application

Use a REST client (Postman / Insomnia) to test endpoints:

POST /register → register new user

POST /login → get JWT token

POST /note → add note

PUT /note/{id}/status → toggle completed/uncompleted

GET /note/{id} → get note by id

GET /notes → get all notes

PUT /note/{id} → update note

DELETE /note/{id} → delete note

DELETE /notes → delete All notes

GET /note/search?keyword=... → search notes by keyword

📌 Notes

All endpoints are secured with JWT

NoteMapper is used for all responses to reduce duplication
