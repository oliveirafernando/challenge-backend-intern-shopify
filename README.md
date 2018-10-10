## Overview
The goal of this document is to describe the artifacts provided in this project.
Here, also is described some steps to build and execute the project.

## 1. Docker Image

### 1.1. Official repository
- https://hub.docker.com/r/oliveirafernando/intern-backend-challenge/

### 1.2. Pulling the image
- docker pull oliveirafernando/inter-backend-challenge

### 1.3. Steps Build and Run the project.

#### 1.3.1. Compiling the image
- sudo ./mvnw install dockerfile:build

#### 1.3.2. Running the project
- docker run -p 8080:8080 -t -d oliveirafernando/intern-backend-challenge

### 1.4. Pushing the image
- docker login
- docker push oliveirafernando/intern-backend-challenge:latest

## 2. API Documentation 
The API methods were described using Swagger. There, were described the methods, the HTTP used verbs and parameters.
- URL: http://localhost:8080/swagger-ui.html

## 3. Database Access
- With the App has been started the database access is provided from a browser client.
- The H2 Console can be accessed by http://localhost:8080/h2/login.jsp
- Credentials:
	- Driver Class: org.h2.Driver
	- JDBC URL: jdbc:h2:mem:testdb
	- User Name: sa
	- Password: <blank>
