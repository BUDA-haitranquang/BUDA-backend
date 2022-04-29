FROM adoptopenjdk/openjdk11
COPY ./src/main/resources/* /resources
COPY ./target/*.jar /backend.jar
ENTRYPOINT ["java","-jar","/backend.jar"]
EXPOSE 8080