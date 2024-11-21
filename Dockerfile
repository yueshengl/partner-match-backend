FROM maven:3.5-jdk-8-alpine as builder
# Copy local code to the container image.
WORKDIR /app
COPY pom.xml .
COPY src ./src
# Build a release artifact.
RUN mvn package -DskipTests
RUN mv /app/target/user-center-0.0.1-SNAPSHOT.jar /app/target/user-center-backend-0.0.1-SNAPSHOT.jar
# Run the web service on container startup.
CMD ["java","-jar","/app/target/user-center-backend-0.0.1-SNAPSHOT.jar","--spring.profiles.active=prod"]
