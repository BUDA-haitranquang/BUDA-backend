FROM adoptopenjdk/openjdk11
COPY ./src/main/resources/application.properties /resources/application.properties
COPY ./target/*.jar /backend.jar
ENTRYPOINT ["java","-jar","/backend.jar"]
EXPOSE 8080