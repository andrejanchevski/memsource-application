# memsource-application
Assignment to become a Memsource Developer

### Architecture

While solving this Task I have developed a simple architecture where I have created an WebService(Backend Service) which acted as a proxy or type of Middleware 
between a custom Frontend Client and the Memsource API. 
The Backend was developed with a monolithic approach with layered architectures having:
  - API Layer
  - Service Layer
  - Repository Layer (Database Layer)

Technologies which I have used to develop my simple solution for communicating with the Memsource API are:
  - Spring, Spring Boot - BE Framework
  - React - FE Framework
  - PostgreSQL - DataBase
  - Docker - Running the Database

### Spring Security

To demonstrate my knowledge skills of Spring Security, aside from the requieremnt to create a page where user can enter his Credentials and to persis
that login configuration in the Proxy Backend DataBase I have implemented additional authentication and authorization for the proxy backend with the help of
Spring Securty and JWT. So the basic idea was in order to User to use my Proxy Backend API it has to authenticate first with the Memsource API (using the token
for Memsource API) and Additionally to authenticate with my Proxy Backend (using token which is recognized by the Proxy Backend API which I have developed.
In order to implement this I have used the advantages of the FilterChain of Spring, JWT Library for generating Token and extracting Data from it, password
encoder to keep the password encoded in the DataBase. My backend api does not have a register functionallity so I after succesfull authentication with
Memsource I am persisting the user.

Representation of Filter Chain (The traveling path of a request). 

Exception Handler Filter -> MemSourceAuthentication Filter -> MyApiAuthenticationFilter -> MyAPIAuthorizationFilter


### Spring Boot Concepts
To demonstrate Spring Boot Logic I have used some Spring and Spring Boot scenarios for different use cases.

 - Scheduling - Schediling configuration so every 10 minutes projects are being Fetched from the Memsource API and persisting to a local DB.
 - FeignClient - Library in order to make API Calls from my web service to MemSource WebService. FeignClient enables us to write simple code for third api
 integrations with syntax really close to a SpringBoot syntax and also behind the scenes it implements interesting concepts such as load balancing.
 - EventPublishing - Throwing events to implement better Code Isolation
 - Specifiation JPA - Using Criteria Builder to easily implement queries which can be used a lot throughout the application
 - FlyWay - Database Versioning
 - Generics in Java. Some of the classes such as PagedEntityControllerTemplate are created to be reusable for example if we need to implement Pagination 
 for additional Entity in our application we just need to use this templates the implementation will be faster and easier.
 - JPA Pagination
 - SLF4J - Spring Logging

### Frontend Concepts

- ContextAPI - way of handling global state management

### Further Improvements

- Reactive Spring and WebClient instead of FeignClient - For better thread usage and making the MemsourceAPI integration in more asychronous nature
- Create Integration Tests for better production quality code. WE need to test end to end communication so IntegrationTesting will be our friend. To do this
we must implement test database and some mocking framework.
- Dockerizing the Whole application - Dockerizing the whole Architecture including the FE and the BE so everything can be started with docker compose up and 
the this application can be started in any environment independent of whether we have locally installed java or nodejs
- Frontend Error Handling
- Registration of Users
- Scheduling should be done in more efficent way for example fetch the latest projects after specific date.

### Starting The Application

 - Backend Starting - Run docker compose up to start up the database and just run the backend application in the editor you have opened it.
 - Frontend Starting - Run npm install and npm start
