FROM maven:3.8.1-openjdk-15-slim AS builder

WORKDIR /build

COPY pom.xml /build
RUN mvn verify --fail-never

COPY src /build/src
RUN mvn package

FROM adoptopenjdk/openjdk15:jdk-15.0.2_7-centos-slim

COPY --from=builder /build/target/virtual-bank-1.0.jar /app/app.jar

ENTRYPOINT ["java", "-jar", "/app/app.jar"]
