FROM openjdk:8
ADD target/docker-gateway-service.jar docker-gateway-service.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "docker-gateway-service.jar"]