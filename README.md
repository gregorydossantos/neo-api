# Neo API
<br/>This is a project of the Neo Take-Home Assignment.

## About project
This project consists of creating an API. I choose use the DDD pattern for structure my code
and implements SOLID and clean code concepts.

## Technologies and Libraries:
- Java 21 
- Spring-boot 4.0.0 
- Mapstruct 1.5.5.Final 
- Openapi 2.0.4 
- Jacoco 0.8.12

### Running API (local):
If you don't already have them, install JDK 17 and Apache Maven (minimum version 3.6 or later), then clone the project 
repository from GitHub, run it with your preferred IDE, and use the collections located in the project's Collection 
directory to make API calls.

### Documentation (Swagger - Endpoints):
After running locally the project, we can access the API documentation through Swagger:
<br/> Link: http://localhost:8080/neo/api/swagger-ui/index.html#/

#### Jacoco Report:
To run and generate a report about coverage tests, run mvn test and after complete you can look at in: 
/target/site/jacoco/index.html.