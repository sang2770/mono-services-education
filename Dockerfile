FROM adoptopenjdk/openjdk11-openj9:ubi-minimal-jre

# Set the current working directory inside the image
WORKDIR /app

COPY ./target/*.jar /app/education-0.0.1.jar
ADD ./src/main/resources /app/config
EXPOSE 80

ENTRYPOINT ["java","-jar","education-0.0.1.jar","--spring.profiles.active=${ENV:dev}"]
