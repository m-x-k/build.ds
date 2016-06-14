FROM java:8

RUN mkdir /app
RUN mkdir /config

ADD build.ds/build/libs/build-ds-0.1.0.jar  /app/
ADD build.ds/config/healthcheck.yml         /config/

EXPOSE 8181

CMD ["java", "-jar", "/app/build-ds-0.1.0.jar", "--spring.config.location=/config/healthcheck.yml,/config/jobserver.yml", "--server.port=8181"]
