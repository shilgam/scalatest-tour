FROM maven:3.5-jdk-8

ARG APP_PATH=/usr/src/app
ARG PROJECT_NAME=demo-app
WORKDIR $APP_PATH

# build all dependencies for offline use
COPY ./$PROJECT_NAME/pom.xml ./$PROJECT_NAME/pom.xml
COPY ./$PROJECT_NAME/conf/settings.xml /usr/lib/mvn/conf/settings.xml
#### workaround for failing scala tests: -Dmaven.test.skip -DskipTests
#### https://github.com/scalatest/scalatest/issues/466#issuecomment-108933560
# RUN mvn -B -e -C -T 1C org.apache.maven.plugins:maven-dependency-plugin:2.8:go-offline -Dmaven.test.skip -DskipTests --file ./$PROJECT_NAME/pom.xml
# RUN mvn dependency:go-offline package -Dmaven.test.skip -DskipTests --batch-mode --errors --file ./$PROJECT_NAME/pom.xml
# RUN mvn dependency:resolve dependency:resolve-plugins -Dmaven.test.skip -DskipTests --batch-mode --errors --file ./$PROJECT_NAME/pom.xml
# RUN mvn org.apache.maven.plugins:maven-dependency-plugin:3.1.0:go-offline -Dmaven.test.skip -DskipTests --batch-mode --errors --file ./$PROJECT_NAME/pom.xml


# copy other files
COPY ./$PROJECT_NAME/src ./$PROJECT_NAME/src


## build for release
# RUN mvn -f ./$PROJECT_NAME/pom.xml -B -e -T 1C verify
# RUN mvn package --batch-mode --errors --file ./$PROJECT_NAME/pom.xml --offline -Dmaven.legacyLocalRepo=true
RUN mvn package --file ./$PROJECT_NAME/pom.xml --batch-mode --errors

## set the startup command to run your binary
CMD ["java", "-jar", "./demo-app/target/demo-app-1.0-jar-with-dependencies.jar"]
