FROM openjdk:17-jdk-slim
LABEL authors="jimmylawson"

WORKDIR /app

# COPY jar file into the image
COPY target/*.jar app.jar

#Run the app
ENTRYPOINT ["java", "-jar", "app.jar"]
