# ROBOT APP

Api rest to control robot collector.

# Getting Started

There is and endpoint '/robot/start' to start the robot. With a valid polyline the robot begins the route saving pollution reads into a mongoDB, and every 15 minutes the robot print the average level of pollution since the last print.

Into the application.yml you can configure the robot such as speed(m/s) and distance(m) between each read.


### Run with docker-compose

In the root of the project run:

```
 ./gradlew bootJar
 
 docker-compose up 
```

This will build and start one container for the rest api and another container with a mongoDb image.


### Run with Docker

In the root of the project build and run image:  

```
 ./gradlew bootJar

 docker build -t robot-app .  
 
 docker run -p 8085:8085 -d --net="host" -it robot-app
```

### Run tests

To test the boot jar you must have a mongodb installed on your computer.
In the root of the project run:

```
 ./gradlew test
```


### Swagger

Once the application is started with docker-compose it can be tested with swagger:

[http://localhost:8085/swagger-ui.html](http://localhost:8085/swagger-ui.html)


## Stack tech

Springboot  
Mongo  
JPA  

## Improving

More unit test with monitoring stations.  
More integration tests.  
Finish endpoints /stop and /reroute (I'm out of time and this is a little bit trickie).  

### Reference Documentation
For further reference, please consider the following sections:

* [Official Gradle documentation](https://docs.gradle.org)
* [Spring Boot Gradle Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.2.6.RELEASE/gradle-plugin/reference/html/)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.2.6.RELEASE/reference/htmlsingle/#boot-features-developing-web-applications)
* [Spring Data MongoDB](https://docs.spring.io/spring-boot/docs/2.2.6.RELEASE/reference/htmlsingle/#boot-features-mongodb)
* [Spring Data JPA](https://docs.spring.io/spring-boot/docs/2.2.6.RELEASE/reference/htmlsingle/#boot-features-jpa-and-spring-data)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/bookmarks/)
* [Accessing Data with MongoDB](https://spring.io/guides/gs/accessing-data-mongodb/)
* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)

### Additional Links
These additional references should also help you:

* [Gradle Build Scans – insights for your project's build](https://scans.gradle.com#gradle)

