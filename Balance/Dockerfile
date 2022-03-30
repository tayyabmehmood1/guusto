FROM openjdk:8-jdk-alpine
COPY target/Balance-0.0.1-SNAPSHOT.jar Balance-0.0.1-SNAPSHOT.jar
COPY target/classes/application.properties application.properties
CMD java -Dspring.config.location=application.properties -jar Balance-0.0.1-SNAPSHOT.jar
