FROM openjdk:8-jdk-alpine
MAINTAINER Sheldan
VOLUME /tmp
ADD bot/crimson.jar /app.jar
ADD config/* /config/
ADD wrapper/*.sh /
RUN chmod +x /start.sh
CMD ["/start.sh"]