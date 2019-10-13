FROM maven:3.6.2-jdk-8

ARG APP_PATH=/usr/src/app/demo-app
WORKDIR $APP_PATH

# install dependencies
COPY ./pom.xml ./pom.xml
# << install dependencies >>

# copy source files
COPY ./src ./src

# build for release
RUN mvn package --batch-mode --errors

# run binary
CMD ["java", "-jar", "./target/demo-app-0.1-SNAPSHOT-jar-with-dependencies.jar"]
