FROM openjdk:17-jdk-alpine
ADD ./target/config.jar /app/
ENTRYPOINT ["java", "-Xmx200m",  "-jar", "/app/config.jar"]