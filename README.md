# Build.ds setup and configuration

Simple build monitor using java spring boot

NOTE: This is still a work in progress!!!


## Configuration
See config/config.json for example setup

## Development run
./gradlew clean build bootRun -Dspring.config.location=PATH/config.json

## Running the jar
java -jar build-ds-*.jar --spring.config.location=PATH/config.json