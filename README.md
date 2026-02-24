# Ticket-Selling-System-

Overview

This project is a Java-based Event Management and Ticketing System developed as a full-stack desktop application.
It provides a complete solution for managing events, customers, reservations, and ticket sales through a structured database-driven architecture.

The system supports both customer operations and administrator management tools, offering a realistic implementation of a commercial ticketing platform.

The application follows a modular architecture and integrates a relational database for persistent data storage.

Key Features
Customer Features

User registration and authentication

Secure login system

Browse available events

Reserve tickets

Purchase tickets

Store and use credit card information

View reservations

Administrator Features

Administrator authentication

Event creation and management

Ticket availability control

Sales monitoring

Reservation monitoring

Statistical analysis of sales

VIP ticket sales analysis

Time-based sales reports

System Architecture

The project follows a layered architecture:

1. Frontend Layer

Graphical User Interface implemented in Java.

Responsibilities:

User interaction

Form handling

Navigation between pages

Data visualization

Example components:

Login Page

Registration Page

Home Page

Events Page

User Dashboard

Admin Dashboard

2. Backend Layer

Responsible for data management and database communication.

Responsibilities:

Database connectivity

Query execution

Data validation

Business logic support

Main components:

Database connection manager

Data access classes

Server logic

3. Data Model Layer

Contains the core application entities.

Entities include:

Customer

Event

Ticket

Reservation

Credit Card

Administrator

These classes represent the system’s domain model and correspond to database tables.

Technologies Used

Programming Language: Java

Build Tool: Maven

Database: MySQL

Database Access: JDBC

User Interface: Java GUI (Swing-based)

Architecture: Layered Architecture (Frontend – Backend – Data Model)

Database Design

The system uses a relational database structure.

Main tables include:

Customers

Administrators

Events

Tickets

Reservations

Credit Cards

The database ensures:

Data consistency

Referential integrity

Structured storage
