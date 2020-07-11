FROM openjdk:8-jdk-alpine
MAINTAINER Sheldan
VOLUME /tmp
ADD lib/*executable-jar.jar /app.jar
ADD lib/*.properties /
ADD lib/*.sh /
RUN chmod +x /start.sh