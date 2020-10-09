# ThreatFabric Challenge

[![CircleCI](https://circleci.com/gh/albihasani94/threatfabric-challenge.svg?style=shield&circle-token=dca5c5bf7d16c819939dac4c44ed988829379edc	)](https://app.circleci.com/pipelines/github/albihasani94)

## Prerequisites

- Maven
- JDK
- Docker

## Build and Deployment

There are for sure so many ideas and school of thoughts around 
development flow and especially continuous integration.

I have thought about two workflows.

One is happening on your machine, and the other out there in the 
CI pipeline.

## Local Development on your machine

This focuses on three steps.

- Build the artifact `jar`
- Build a docker image out of this artifact
- Compose this image along a postgres container

### Running it straight

If you want to get going quickly run straight `build.sh`.

```bash
chmod a+x build.sh

./build.sh
```

### If you want to run it from your IDE

Setup a postgres container first.

```bash
docker-compose -f compose-postgres.yml up
```

Run the project normally in IDE.

## Status check via actuator

```bash
curl -X GET http://localhost:8080/api/actuator/health
```

should return

```bash
{"status":"UP"}
```

## CI Pipeline on CI server

This focuses on three steps.

- Push to github branch
- CircleCI takes over:
  - Runs the maven package
  - If tests pass
    - Build the docker image
    - Push artifact to docker hub
- Pull the image via docker-compose and run along the postgres service
