# Jenkins Dockerfile for test purposes

## Requires

* Docker

## Create image

`docker build -t build-ds-jenkins .`

## Run container

`docker run --name my-build-ds-jenkins -p 8080:8080 -p 50001:50001 --env JENKINS_SLAVE_AGENT_PORT=50001 build-ds-jenkins`
