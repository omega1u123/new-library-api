FROM openjdk:17.0.2-jdk-slim-buster
ARG JAR_FILE=target/library-api-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} lib-api.jar
ENTRYPOINT ["java", "-jar", "/lib-api.jar"]