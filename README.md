# Build.ds setup and configuration

Simple build monitor using java spring boot

NOTE: This is still a work in progress!!!


## Configuration
See build.ds/config/jobserver.json for example jenkins job setup and build.ds/config/healthcheck.json for the healthcheck endpoint setup.

## Development run
./gradlew clean build bootRun -Dspring.config.location=PATH/*.yml

## Running the jar
java -jar build-ds-*.jar --spring.config.location=PATH/*.yml

## Badges

[![Build Status](https://travis-ci.org/m-x-k/build.ds.svg?branch=master)](https://travis-ci.org/m-x-k/build.ds)

[![codecov](https://codecov.io/gh/m-x-k/build.ds/branch/master/graph/badge.svg)](https://codecov.io/gh/m-x-k/build.ds)
