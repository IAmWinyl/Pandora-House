FROM openjdk:20-jdk
ARG JAR_FILE=target/pandorahouse-0.0.1.jar
ADD ${JAR_FILE} app.jar
EXPOSE 8081
ENTRYPOINT ["java","-jar","/app.jar"]