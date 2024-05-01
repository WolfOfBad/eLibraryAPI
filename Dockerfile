FROM openjdk:21
ADD build/libs eLibraryApi-0.0.1.jar
ENTRYPOINT java -jar app.jar