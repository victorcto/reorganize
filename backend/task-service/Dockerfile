FROM openjdk:17

COPY ./target/task-service*.jar /app/task-service.jar
CMD ["java", "-Xmx200m", "-jar", "/app/task-service.jar"]

EXPOSE 8080