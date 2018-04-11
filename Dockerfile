FROM openjdk:8-jre-alpine
MAINTAINER Nicolas Fränkel <nicolas at frankel.ch>

ARG JAR_FILE

ADD ${JAR_FILE} /usr/share/renamer.jar

ENTRYPOINT ["/usr/bin/java", "-jar", "/usr/share/renamer.jar"]