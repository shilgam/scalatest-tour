# scala-tour

## Prerequisites

1. Docker and docker-compose installed
1. Maven installed

## Usage

1. Clone the repo

1. Download dependencies and build docker image

        mvn -f ./demo-app/pom.xml clean package

1. Run app:

        docker-compose up

### Test suite

1. Run test suite

        mvn test
