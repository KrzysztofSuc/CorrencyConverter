FROM openjdk:14-ea-15-jdk-alpine3.10
ADD target/currencyConverter-0.0.1-SNAPSHOT.jar .
EXPOSE 8080
CMD java -jar currencyConverter-0.0.1-SNAPSHOT.jar