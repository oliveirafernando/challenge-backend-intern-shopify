# challenge-backend-intern-shopify

Challenge to became intern at Shopify

## Overview
The goal of this document is describe the artifacts provided in this project.
Here, also is described some steps to build and execute the project.

## 1. Docker Image:

### 1.1. Official repository
- https://hub.docker.com/r/oliveirafernando/intern-backend-challenge/

### 1.2 Pulling the image
- docker pull oliveirafernando/inter-backend-challenge

### 1.3 Steps to generate image, push to Docker Hub and running project.
#### 1.3.1 Compiling and generating the image
- sudo ./mvnw install dockerfile:build

#### 1.3.2.Tagging image
- docker tag springio/intern-backend-challenge:latest oliveirafernando/intern-backend-challenge:latest

#### 1.3.3. Pushing the image
- docker login
- docker push oliveirafernando/intern-backend-challenge:latest

#### 1.3.4 Running the project
- docker run -p 8080:8080 -t oliveirafernando/intern-backend-challenge

## 2. API Documentation 
- URL: http://localhost:8080/swagger-ui.html
The API methods were described using Swagger. There, were described the methods, the HTTP used verbs and parameters. 

## 3. Database Access
- The database client can be accessed by http://localhost:8080/h2/login.jsp
- Credentials (no password):
	- User Name: sa
	- Password: 