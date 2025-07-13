# Use slim OpenJDK 17 base image
FROM openjdk:17-jdk-slim

# Set working directory
WORKDIR /app

COPY src/main/resources/mobileMoneyTransactions.csv /app/mobileMoneyTransactions.csv

# Copy the fat jar (with all dependencies) into the image
COPY target/backend-java-1.0.0.jar app.jar

# Expose any port your app listens on (8080 only if you're exposing REST)
EXPOSE 8080

# Run the app
ENTRYPOINT ["java", "-jar", "app.jar"]