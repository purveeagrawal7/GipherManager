FROM openjdk:16-alpine3.13
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} accountmanager-1.0.jar
ENV MYSQL_ROOT_PASSWORD=Nov@1992
ENTRYPOINT ["java", "-jar","/accountmanager-1.0.jar"]