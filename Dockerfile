FROM openjdk:17-jdk-alpine
EXPOSE 8082
COPY target/kaddem-5.0.0.jar kaddem-5.0.0.jar
ENTRYPOINT ["java","-jar","/kaddem-5.0.0.jar"]
