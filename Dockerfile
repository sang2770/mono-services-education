#
# Build stage
#
FROM maven:3.8.2-jdk-11 AS build
COPY . .
RUN mvn -s settings.xml package -Pprod -DskipTests


FROM adoptopenjdk/openjdk11-openj9:ubi-minimal-jre

# Set the current working directory inside the image
WORKDIR /tmp

COPY --from=build /target/*.jar education-0.0.1.jar
ADD ./src/main/resources /app/config
EXPOSE 80

ENTRYPOINT ["java","-jar","education-0.0.1.jar","--spring.profiles.active=${ENV:dev}"]
