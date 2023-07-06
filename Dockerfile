FROM maven:3.9.3-eclipse-temurin-17 AS builder
WORKDIR /tmp
COPY ./src ./src
COPY ./pom.xml .
RUN mvn package
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "./target/api-0.0.1-SNAPSHOT.jar"]