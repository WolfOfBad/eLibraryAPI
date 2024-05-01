FROM openjdk:21
ADD target/scrapper.jar app.jar
ENTRYPOINT java -jar app.jar