# scala-tour

## Prerequisites

1. Docker and docker-compose installed

## Usage

1. Clone the repo

1. Download dependencies and build docker image

        docker-compose build

1. Run app:

        docker-compose up

### Test suite

1. Run test suite

        docker-compose run --rm app mvn -f ./demo-app/pom.xml test
