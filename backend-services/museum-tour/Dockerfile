FROM maven:3.8.1-openjdk-15-slim AS builder

COPY target/*.jar /app/app.jar

WORKDIR /app

ENTRYPOINT ["java", "-jar", "/app/app.jar"]
