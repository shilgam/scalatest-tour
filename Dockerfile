ARG DIGEST=sha256:e04922091c37c716e0f743a482c35b87ef344aa08fd111ebaa60c6f8dc89777b
FROM yogihardi/alpine-scala-maven@${DIGEST}

ARG APP_PATH=/usr/src/app
ARG PROJECT_NAME=demo-app
WORKDIR $APP_PATH

# build all dependencies for offline use
COPY ./$PROJECT_NAME/pom.xml ./$PROJECT_NAME/pom.xml
RUN mvn -f ./$PROJECT_NAME/pom.xml dependency:go-offline -B

# copy other files
COPY ./$PROJECT_NAME/src ./$PROJECT_NAME/src

# build for release
RUN mvn -f ./$PROJECT_NAME/pom.xml package

# set the startup command to run your binary
CMD ["java", "-jar", "./demo-app/target/demo-app-1.0-jar-with-dependencies.jar"]
