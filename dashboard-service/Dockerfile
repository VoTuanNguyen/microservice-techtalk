FROM openjdk:8
ADD target/docker-dashboard-service.jar docker-dashboard-service.jar
EXPOSE 9090
ENTRYPOINT ["java", "-jar", "docker-dashboard-service.jar"]