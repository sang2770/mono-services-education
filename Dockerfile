#
# Build stage
#
FROM maven:3.8.1-openjdk-11 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn package -DskipTests


# Build docker
FROM openjdk:11-jre-slim
WORKDIR /app
COPY --from=build /app/target/*.jar education-0.0.1.jar
# ADD ./src/main/resources /app/config
EXPOSE 80 9999

ENTRYPOINT ["java","-jar","education-0.0.1.jar"]
