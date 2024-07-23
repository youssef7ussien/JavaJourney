# Stage 1: Build the application
FROM maven:3.8.6-eclipse-temurin-17-alpine AS build

# Set the working directory in the container
WORKDIR /app

# Copy the project files to the build stage
COPY . .

# Install pandoc and other utils for converting README file to HTML
RUN apk add --no-cache pandoc curl git

# Clones easy-pandoc-templates repo to add theme to html file (https://github.com/ryangrose/easy-pandoc-templates)
RUN curl 'https://raw.githubusercontent.com/ryangrose/easy-pandoc-templates/master/copy_templates.sh' | sh

# Convert README.md to README.html
RUN pandoc README.md -o src/main/resources/static/README.html --template=easy_template.html --toc

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
