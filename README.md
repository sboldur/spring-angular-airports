# Description

Simple Web application example for queries and reports on stored data.
Backend: java based REST application that uses Spring Data for exposing the following endpoints:
 * GET /api/query/{country code or name} - returns all airports and runways of the provided parameter country code or name)
 * GET /api/reports - returns the following reports type:
    * top 10 countries with the highest no. of airports with count
    * top 10 countries with the lowest no. of airports with count
    * top 10 most common runway identifications
    * type of runway surface per each country

Frontend: angular based application that uses $http service for communication with server

 ## This project was generated with:
  * [Spring Boot](https://github.com/spring-projects/spring-boot)  version 1.5.7 
  * [Angular CLI](https://github.com/angular/angular-cli) version 1.4.3.
 
 ## This project uses:
  #### Backend:
   * H2 in-memory Database
   * Spring-Data-JPA
   * Swagger version 2.6.1 
  #### Frontend
   * Angular v4 
   * bootstrap3 and ngx-bootstrap

# Start up the app

## Server
Run `mvn spring-boot:run` to start up and run the server. Navigate to `localhost:8080/swagger-ui.html` and check the exposed endpoints.
#### Build
Run `mvn clean install` to build the project.The build artifacts will be stored in the `dist/` directory.

## Client
Run `ng serve` for a dev server. Navigate to `http://localhost:4200/`. The app will automatically reload if you change any of the source files.
#### Build
Run `ng build` to build the project. The build artifacts will be stored in the `dist/` directory.
