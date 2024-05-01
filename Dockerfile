FROM gradle:8.7.0-jdk21-alpine AS build
COPY . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build --no-daemon

FROM openjdk:21
COPY --from=build /home/gradle/src/build/libs/*.jar /app/app.jar

ENTRYPOINT java -jar /app/app.jar