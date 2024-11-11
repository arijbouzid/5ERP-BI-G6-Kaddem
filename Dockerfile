FROM openjdk:17-jdk

COPY target/kaddem-0.0.1-SNAPSHOT.jar .

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "kaddem-0.0.1-SNAPSHOT.jar"]
