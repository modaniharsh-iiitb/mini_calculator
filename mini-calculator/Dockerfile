# Use Maven to build the app
FROM maven:3.9.6-eclipse-temurin-17 as builder
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Run the app with a JDK image
FROM eclipse-temurin:17-jdk
WORKDIR /app
COPY --from=builder /app/target/myapp.jar myapp.jar
CMD ["java", "-jar", "myapp.jar"]
