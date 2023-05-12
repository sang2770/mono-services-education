#
# Build stage
#
FROM maven:3.8.1-openjdk-11 AS build
WORKDIR /app
COPY . .
RUN mvn -s settings.xml clean package -DskipTests


# Build docker
FROM openjdk:11-jre-slim
WORKDIR /app
COPY --from=build /app/target/*.jar education-0.0.1.jar
# ADD ./src/main/resources /app/config
EXPOSE 80 9999

ENTRYPOINT ["java","-jar","education-0.0.1.jar"]
