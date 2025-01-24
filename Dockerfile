FROM maven:3.8.6-eclipse-temurin-17 as builder
WORKDIR /app
COPY mvnw ./
COPY pom.xml ./
COPY ./src ./src
RUN mvn clean install -DskipTests

FROM openjdk:24-slim-bullseye
WORKDIR /app
EXPOSE 8080
COPY --from=builder /app/target/*.jar /app/appl.jar
ENTRYPOINT ["java", "-jar", "appl.jar"]