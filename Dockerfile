FROM openjdk:8-jdk-alpine
ADD eatwithrandom-1.0.0.jar app.jar
EXPOSE 4444
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]