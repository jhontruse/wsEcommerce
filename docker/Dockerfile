# docker/Dockerfile
FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

COPY target/ecommerce-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 7073

ENTRYPOINT ["java", "-jar", "app.jar"]