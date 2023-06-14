FROM openjdk:20-jdk
VOLUME /tmp
EXPOSE 8080
ARG JAR_FILE=target/pandorahouse-0.0.1.jar
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]