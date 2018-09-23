# challenge-backend-intern-shopify

Challenge to became intern at Shopify

### The database client can be accessed by http://localhost:8080/h2/login.jsp
- Credentials:
	- User Name: sa
	- Password: 
	
### The API documentation can be accessed by http://localhost:8080/swagger-ui.html

### Building Docker Container. Commands:
- sudo ./mvnw install dockerfile:build
- sudo ./mvnw dockerfile:push

### Docker commands
- List images:
	- docker images
	
- List containers:
	- docker container ls

- Run:
	- docker run -p 8080:8080 -t springio/intern-backend-challenge

- Stop:
	- docker stop <container_ID>

- Delete container:
	- docker rm <container_ID>
	
- Delete image:
	- docker rmi <image_ID>

- Tag image:
	- docker tag springio/intern-backend-challenge:latest oliveirafernando/inter-backend-challenge:shopify_developer_challenge 
	- docker push oliveirafernando/inter-backend-challenge:shopify_developer_challenge
	
- Repository:
	- https://hub.docker.com/r/oliveirafernando/inter-backend-challenge/
	- docker pull oliveirafernando/inter-backend-challenge

### Docker extra commands
- Run with Spring profiles - dev: 
	- $ docker run -e "SPRING_PROFILES_ACTIVE=dev" -p 8080:8080 -t springio/intern-backend-challenge
	
- Run with Spring profiles - prod:
	- $ docker run -e "SPRING_PROFILES_ACTIVE=prod" -p 8080:8080 -t springio/gs-spring-boot-docker
	
