# Software Requirements Specification
## 1. Introduction
### 1.1 Purpose
The purpose of the Network Ticketing Management System is to provide an efficient platform for managing incidents related to the customer's network inventory. This system aims to streamline incident management by allowing customers to log issues, assigning them to appropriate groups, and enabling designated users to resolve incidents.

### 1.2 Scope
The Network Ticketing Management System will focus on:

Establishing relationships between different network elements.

Implementing user roles for customers and support personnel.

Allowing incident logging with specific details.

Automated assignment and categorization of incidents.

Resolution of incidents by authorized users.

Tracking and monitoring the status of incidents.
## 2. System Overview
The Network Ticketing Management System will be a web-based application with a user-friendly interface. It will be developed to ensure compatibility with modern browsers. The system will rely on a database to store network inventory, incident data, user information, and other relevant details.

## 3. Functional Requirements
### 3.1 Network Inventory Relationship
The system shall establish relationships between different network elements stored in the database.

### 3.2 User Roles
The system shall implement user roles, including roles for customers to log incidents and roles for working on reported incidents.

### 3.3 User Authentication
The system shall provide user authentication with assigned roles.
#### 3.4 Incident Logging
Customers shall be able to log incidents by:

1)Choosing a network element through search functionality.
2)Checking related components deployed with the selected network element.
3)Reporting specific issues (e.g., power problems, not reachable, port not working).
4)Creating a ticket with appropriate details.
#### 3.5 Assignment and Categorization
The system shall automatically select the assignment group based on the type of issue reported.
The system shall capture severity and priority of the incident.
### 3.6 Incident Resolution
Authorized users shall be able to work on reported incidents.

The system shall provide the ability to:
1) Add resolution comments.
2) Reassign the incident to another user or group.
3) Add a root cause to the resolution.
### 3.7 Incident Tracking
Users shall be able to view a list of issues currently in various states within the system.
## 4. Dev/Execution Platform  
Angular-ReactJS, Any open source DB, API using any opensource app servers
 ## 5. Conclusion
The Network Ticketing Management System aims to provide a comprehensive solution for incident management within the customer's network inventory. This document outlines the functional and non-functional requirements that will guide the development and implementation of the system.
 
