FROM openjdk:11
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} rooms-bot-admin.jar
ENTRYPOINT ["java","-jar","/rooms-bot-admin.jar"]