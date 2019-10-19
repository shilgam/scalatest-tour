# scala-tour

[![Build Status](https://travis-ci.com/shilgam/scalatest-tour.svg?branch=master)](https://travis-ci.com/shilgam/scalatest-tour)

## Prerequisites

1. Docker and docker-compose installed

## Usage

1. Clone the repo

1. build docker image and launch the app

        docker-compose up --build

### Run test suite

1. Run unit tests:

        docker-compose -f docker-compose.yml -f docker-compose.test.yml run --rm app mvn --offline test

## Additional info

* ScalaTest User Guide: http://www.scalatest.org/user_guide
