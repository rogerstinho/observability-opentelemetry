# Use a base image with Java installed
FROM openjdk:21-slim

# Set the working directory in the container
WORKDIR /app

# Make port 8080 available to the world outside this container
EXPOSE 8080

# Copy the JAR file into the container at /app
COPY ./target/*.jar application.jar

COPY ./opentelemetry-javaagent.jar opentelemetry-javaagent.jar

# Specify the command to run your application
ENTRYPOINT ["java", \
            "-javaagent:/app/opentelemetry-javaagent.jar", \
            "-jar", \
            "/app/application.jar" \
         ]