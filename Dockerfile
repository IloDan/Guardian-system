FROM openjdk:11-ea-9-jre-slim
ARG SERVICE
ARG JAR_FILE=${SERVICE}/target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom","-Dspring.profiles.active=docker.compose","-Duser.timezone=GMT+01 $JAVA_OPTS","-jar","/app.jar"]