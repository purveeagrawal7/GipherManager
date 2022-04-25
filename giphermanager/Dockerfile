FROM openjdk:16-alpine3.13
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} giphermanager-1.0.jar
ENV MONGO_URL=mongodb://localhost:27017/FavouriteGifDB
ENV MONGO_DATABASE=FavouriteGifDB
ENTRYPOINT ["java","-jar","/giphermanager-1.0.jar"]