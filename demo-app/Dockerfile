FROM openjdk:8
ARG JAR_FILE
RUN echo $JAR_FILE
COPY ${JAR_FILE} /demo-app.jar
ENTRYPOINT ["java", "-jar", "/demo-app.jar"]
