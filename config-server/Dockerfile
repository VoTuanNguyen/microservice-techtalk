FROM openjdk:8
ADD target/docker-config-server.jar docker-config-server.jar
EXPOSE 8888
ENTRYPOINT ["java", "-jar", "docker-config-server.jar"]