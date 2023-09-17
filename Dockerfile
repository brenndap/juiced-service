FROM amazoncorretto:17-alpine-jdk

# create a directory for the app
WORKDIR /app

# copy everything to the app directory
COPY . .

# build project
RUN ./gradlew clean build -x test

# expose on port 8080
EXPOSE 8080

CMD ["java", "-jar", "./buld/libs/juiced-0.0.1-SNAPSHOT.jar"]