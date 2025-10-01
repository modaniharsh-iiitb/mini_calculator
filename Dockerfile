# Use Maven to build the app
FROM maven:3.9.6-eclipse-temurin-17 as builder
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests && cp target/*.jar target/mini-calculator.jar

# Run the app with a JDK image
FROM eclipse-temurin:17-jdk
WORKDIR /app
COPY --from=builder /app/target/mini-calculator.jar mini-calculator.jar
CMD ["java", "-jar", "mini-calculator.jar"]
