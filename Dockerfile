FROM openjdk:17-jdk-slim
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} reading.jar
ENTRYPOINT ["java","-jar","/reading.jar"]