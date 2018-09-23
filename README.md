# challenge-backend-intern-shopify

Challenge to became intern at Shopify

## Overview
The goal of this document is describe the artifacts provided in this project.
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
- The database used in this app is an in memory embedded H2 Database.
- To access the database schema you must download source code from the git repository and then run using maven as described bellow.
- Requirements:
	- Jdk 1.8;
	- Maven 3;

### 3.1. Downloading code from repository using a Git client
- git clone https://gitlab.com/oliveirafernando/challenge-backend-intern-shopify.git

### 3.2. Running app with Maven
- mvn spring-boot:run

### 3.3. H2 Console
- The H2 Console can be accessed by http://localhost:8080/h2/login.jsp
- Credentials:
	- Driver Class: org.h2.Driver
	- JDBC URL: jdbc:h2:mem:testdb
	- User Name: sa
	- Password: <blank>