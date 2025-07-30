# Use a slim OpenJDK 21 base image
FROM openjdk:21-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the Maven build output (JAR file) into the container
COPY target/demo-0.0.1-SNAPSHOT.jar app.jar

# Expose port 8080 (Spring Boot default port)
EXPOSE 80

# Run the JAR file
ENTRYPOINT ["java", "-jar", "app.jar"]