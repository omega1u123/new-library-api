FROM openjdk:17.0.2-jdk-slim-buster
ARG JAR_FILE=target/library-service-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} lib-service.jar
ENTRYPOINT ["java", "-jar", "/lib-service.jar"]