FROM openjdk:16-alpine3.13
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} eurekaserver-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar","/eurekaserver-0.0.1-SNAPSHOT.jar"]