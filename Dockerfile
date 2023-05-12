FROM maven:3.8.3-jdk-11-slim AS builder
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src/ /src/
RUN mvn package

FROM adoptopenjdk/openjdk11-openj9:ubi-minimal-jre

# Set the current working directory inside the image
WORKDIR /tmp

COPY target/*.jar /app/education-0.0.1.jar
ADD ./src/main/resources /app/config
EXPOSE 80

ENTRYPOINT ["java","-jar","education-0.0.1.jar","--spring.profiles.active=${ENV:dev}"]
