FROM openjdk:8-jdk-alpine
COPY target/GiftCard-0.0.1-SNAPSHOT.jar GiftCard-0.0.1-SNAPSHOT.jar
COPY target/classes/application.properties application.properties
CMD java -Dspring.config.location=application.properties -jar GiftCard-0.0.1-SNAPSHOT.jar
