# Employee Reimbursement System (ERS)
Introduction
The Employee Reimbursement System (ERS) is a web-based application that allows employees to submit and track their reimbursement requests. Administrators can review and approve or deny these requests. The system uses Spring Boot for the backend, ReactJS for the frontend, and an SQL database for data storage.

# Features
User Authentication: Secure login and registration.
Role-Based Access Control: Different functionalities for Admin and User roles.
Reimbursement Submission: Employees can submit reimbursement requests.
Reimbursement Tracking: Employees can track the status of their reimbursement requests.
Admin Dashboard: Admins can view, approve, or deny reimbursement requests.
Technologies Used
Backend: Spring Boot, Spring Security, JWT for authentication
Frontend: ReactJS
Database: SQL (e.g., MySQL, PostgreSQL)
Others: Lombok, Hibernate, Jakarta Persistence (JPA), Jakarta Validation
Getting Started
Prerequisites
Java 17+
Node.js 16+
npm 8+
MySQL or PostgreSQL

# Installation
* Clone the repository:
git clone https://github.com/gitamandeep/ERSystem.git
* cd ERSystem

# Backend Setup:
* Navigate to the backend directory:
* cd ers-backend/ers
* Update the application.properties file with your database credentials.
* Build and run the backend application:
* ./mvnw clean install
* ./mvnw spring-boot:run

# Frontend Setup:
* Navigate to the frontend directory:
* bash
* Copy code
* cd ers-frontend
* Install the dependencies and run the frontend application:
* bash
* Copy code
* npm install
* npm run dev
  
# Configuration
* Backend:

Update src/main/resources/application.properties with your database configurations and other properties:

# properties
* Copy code
* spring.datasource.url=jdbc:mysql://localhost:3306/ers
* spring.datasource.username=root
* spring.datasource.password=yourpassword
* spring.jpa.hibernate.ddl-auto=update

# JWT properties
* jwt.secret=yourSecretKey
* jwt.expiration=3600000

# CORS configuration
* myapp.cors.origin=http://localhost:3000
* Frontend:

Update the API endpoints in src/config.js:

javascript
Copy code
export const API_BASE_URL = 'http://localhost:8080/api/v1';
Usage
User Authentication
Login: Users can log in with their credentials.
Register: New users can sign up by providing their details.
Reimbursement Requests
Submit a Request: Users can submit a reimbursement request by providing expense details.
Track Requests: Users can view the status of their submitted requests.
Admin Dashboard
View Requests: Admins can view all reimbursement requests.
Approve/Deny Requests: Admins can approve or deny reimbursement requests.
# API Endpoints
### Authentication:

- POST /api/v1/auth/login: User login
- POST /api/v1/auth/create-user: Create a new user
### Reimbursements:

- GET /api/v1/reimbursements: Get all reimbursements (Admin)
- GET /api/v1/reimbursements/{id}: Get reimbursement by ID
- POST /api/v1/reimbursements: Create a new reimbursement request
- PUT /api/v1/reimbursements/{id}: Update reimbursement status (Admin)
# Contributing
Contributions are welcome! Please follow these steps:

* Fork the repository
* Create a new branch (git checkout -b feature/your-feature)
* Commit your changes (git commit -am 'Add new feature')
* Push to the branch (git push origin feature/your-feature)
* Create a new Pull Request
