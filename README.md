# ThreatFabric Challenge

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

## Local Development

This focuses on three steps.

- Build the artifact `jar`
- Build a docker image out of this artifact
- Compose this image along a postgres container

A script of these steps is located at `build.sh`.

```bash
chmod a+x build.sh
./build.sh
```

### Run the maven local development build

```bash
mvn clean package
```

### Build the docker image

```bash
docker build -t threatfabric-challenge:0.0.1 .
```

### Compose this image along a postgres container

```bash
docker-compose up
```

## CI Pipeline

This focuses on three steps.

- Push to github branch
- CircleCI takes over:
  - Runs the maven package
  - If tests pass
    - Build the docker image
    - Push artifact to docker hub
- Pull the image via docker-compose and run along the postgres service