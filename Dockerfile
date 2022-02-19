FROM openjdk:11
WORKDIR /
EXPOSE 8080
COPY target/*.jar scrapert.jar
CMD java -jar scrapert.jar
