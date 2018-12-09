FROM openjdk:8
ADD target/docker-eureka-discovery.jar docker-eureka-discovery.jar
EXPOSE 8761
ENTRYPOINT ["java", "-jar", "docker-eureka-discovery.jar"]