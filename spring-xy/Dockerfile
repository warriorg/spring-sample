#FROM openjdk:12-alpine
FROM openjdk:11.0.11-jdk-slim

MAINTAINER warrior gao

ENV SPRING_OUTPUT_ANSI_ENABLED=ALWAYS \
    JAVA_OPTS="" \
    PROFILES="default"

ADD build/libs/*.jar /bookstore.jar

ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar /bookstore.jar --spring.profiles.active=$PROFILES"]

EXPOSE 8080