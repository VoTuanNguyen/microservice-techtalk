FROM openjdk:8
ADD target/docker-customer-service.jar docker-customer-service.jar
EXPOSE 7070
ENTRYPOINT ["java", "-jar", "docker-customer-service.jar"]