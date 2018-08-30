FROM openjdk:8-jdk-alpine
LABEL maintainer="emilwojewodka@gmail.com"
EXPOSE 8080
VOLUME /tmp
ARG JAR_FILE=target/inteca-01.jar
COPY ${JAR_FILE} inteca-01.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/inteca-01.jar"]