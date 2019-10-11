ARG DIGEST=sha256:e04922091c37c716e0f743a482c35b87ef344aa08fd111ebaa60c6f8dc89777b
FROM yogihardi/alpine-scala-maven@${DIGEST}

ARG APP_PATH=/usr/src/app
WORKDIR $APP_PATH

ARG PROJECT_NAME=demo-app
ARG PROJECT_PATH=$APP_PATH/$PROJECT_NAME

# copy your source tree
COPY ./ ./

# build for release
RUN cd $PROJECT_PATH && mvn package

# set the startup command to run your binary
CMD ["java", "-jar", "./demo-app/target/demo-app-1.0-jar-with-dependencies.jar"]
