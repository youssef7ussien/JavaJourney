# Stage 1: Build the application
FROM maven:3.8.6-eclipse-temurin-17-alpine AS build

# Set the working directory in the container
WORKDIR /app

# Copy the project files to the build stage
COPY . .

# Build the application
RUN ./mvnw -Pprod -DskipTests clean package

# Stage 2: Run the application
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

# Copy the built jar from the first stage
COPY --from=build /app/target/jwallet-0.0.1-SNAPSHOT.jar /app/

# Expose the port the application runs on
EXPOSE 8080

# Run the jar file
ENTRYPOINT ["java", "-jar", "jwallet-0.0.1-SNAPSHOT.jar"]
