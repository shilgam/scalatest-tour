FROM maven:3.6.2-jdk-8

ARG APP_PATH=/usr/src/app/demo-app
WORKDIR $APP_PATH

# build dependencies
COPY ./pom.xml ./pom.xml
RUN mvn package \
  # skip test: https://github.com/scalatest/scalatest/issues/466#issuecomment-108933560
  --define maven.test.skip --define skipTests \
  --batch-mode --errors

# copy source files
COPY ./src ./src

# build for release
RUN mvn verify --batch-mode --errors --offline

# run binary
CMD ["java", "-jar", "./target/demo-app-1.0-SNAPSHOT-jar-with-dependencies.jar"]
