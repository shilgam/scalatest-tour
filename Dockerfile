FROM maven:3.6.2-jdk-8

ARG APP_PATH=/usr/src/app
ARG PROJECT_NAME=demo-app
WORKDIR $APP_PATH

# build all dependencies for offline use
COPY ./$PROJECT_NAME/pom.xml ./$PROJECT_NAME/pom.xml
# COPY ./$PROJECT_NAME/conf/settings.xml /usr/share/maven/conf/settings.xml
#### workaround for failing scala tests: -Dmaven.test.skip -DskipTests
#### https://github.com/scalatest/scalatest/issues/466#issuecomment-108933560
# RUN mvn -B -e -C -T 1C org.apache.maven.plugins:maven-dependency-plugin:2.8:go-offline -Dmaven.test.skip -DskipTests --file ./$PROJECT_NAME/pom.xml
# RUN mvn dependency:go-offline -Dmaven.test.skip -DskipTests --batch-mode --errors --file ./$PROJECT_NAME/pom.xml
RUN mvn dependency:go-offline dependency:resolve dependency:resolve-plugins package -Dmaven.test.skip -DskipTests --batch-mode --errors --file ./$PROJECT_NAME/pom.xml



# RUN mvn dependency:resolve dependency:resolve-plugins -Dmaven.test.skip -DskipTests --batch-mode --errors --file ./$PROJECT_NAME/pom.xml
# RUN mvn org.apache.maven.plugins:maven-dependency-plugin:3.1.0:go-offline -Dmaven.test.skip -DskipTests --batch-mode --errors --file ./$PROJECT_NAME/pom.xml


# copy other files
COPY ./$PROJECT_NAME/src ./$PROJECT_NAME/src


###### build for release
#### RUN mvn dependency:resolve dependency:resolve-plugins --file ./$PROJECT_NAME/pom.xml --batch-mode --errors -Dmaven.legacyLocalRepo=true
#### RUN mvn package --file ./$PROJECT_NAME/pom.xml --batch-mode --errors -Dmaven.legacyLocalRepo=true --offline
########### RUN mvn org.apache.maven.plugins:maven-dependency-plugin:3.1.0:go-offline --file ./$PROJECT_NAME/pom.xml --batch-mode --errors -Dmaven.repo.local=/root/.m2/repository


RUN echo '>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>'
RUN mvn verify --file ./$PROJECT_NAME/pom.xml --batch-mode --errors -Dmaven.legacyLocalRepo=true -Dmaven.repo.local=/root/.m2/repository --offline
## set the startup command to run your binary
CMD ["java", "-jar", "./demo-app/target/demo-app-1.0-SNAPSHOT-jar-with-dependencies.jar"]
