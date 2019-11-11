# DB1 Group - Java (Spring) Boilerplate

This is an open source MIT project that has been made to facilitate the development of spring applications.

Project Stack:

 * [Java 13](https://www.oracle.com/technetwork/java/13-relnote-issues-5460548.html)
 * [Spring 2.2.0](https://spring.io)
 * [Maven](https://maven.apache.org/)
 * [H2 Database 1.4.99](https://www.h2database.com/html/main.html) - Removable
 * [Keycloak](https://www.keycloak.org/)
 * [Postgres TestContainer 1.12.3](https://www.testcontainers.org/)
 * [ArchUnit 0.12.0](https://www.archunit.org/userguide/html/000_Index.html)
 
## Architecture explanation

### Layered Architecture

Is used the 3 layer architecture where there is openned and closed layers, beeing:
  * **Presentation Layer:** resource package
  * **Business Layer:** service, entity, domain package
  * **Data Layer:** repository package
  * **Cross Cutting:** any utility package, example: adapter
  
This project utilizes ArchUnit to integrity of the architecture. (default-api/test/java/br.com.project/ArchTest.java) 
  
## Project setup

This version of the boilerplate needs the maven to run, and need to have one jdk that is compatible (preferably openjdk13)

### Compile
```
mvn install
```

### Run only tests
```
mvn tests
```

### Start project
```
cd default-api
mvn spring-boot:run
```


