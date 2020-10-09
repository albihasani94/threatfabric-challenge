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

Run the project normally from the IDE.

Equivalent to:

```bash
mvn clean package

java -jar rest/target/rest-0.0.1-SNAPSHOT.jar
```

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

## Performing a few requests

Posting data to Server

There is one endpoint for sending detections to the api.

`http://localhost:8080/api/detections/`.

This endpoint accepts polymorphic detections as its request body. Here is a list containing all three types of detections and a device.

```json
{
  "detections": [
    {
    "detectionUuid": "64ca0ad7-baf0-4b62-b7c7-9c8bcdb468a",
    "nameOfApp": "1st on iPhone 13",
    "time": 1,
    "type": "new",
    "typeOfApp": "Banking"
  },
  {
    "detectionUuid": "50c3b302-5688-40c7-98d0-f6c82295144d",
    "time": 1,
    "type": "resolved"
  },
  {
    "time": 1,
    "type": "no_threats"
  }
  ],
  "device": {
    "deviceId": "9f64824c-0a54-4545-9774-b4a94f3e722e",
    "deviceModel": "iPhone 13",
    "deviceType": "IOS",
    "osVersion": "1"
  }
}
```

Reading data from the server

`http://localhost:8080/api/detections/` GET on this resource will return all detections, of various types (new, resolved, no_threats)

`http://localhost:8080/api/detections?deviceType=IOS` GET on this resource would return all detections made on device type `IOS`. Other filters that you can add are `id`, `detectionUuid` and `nameOfApp`.

`http://localhost:8080/api/detections/` GET on this resource will return all detections.

`http://localhost:8080/api/devices/9f64824c-0a54-4545-9774-b4a94f3e720e/detections/` GET on this resource would return all detections for the device id in the path.

There requests would result in responses to the following example:

```json
[
  {
    "type": "new",
    "id": 64,
    "time": 1,
    "detectionUuid": "16a9d528-fb60-4ec3-8891-7b99df18f914",
    "nameOfApp": "1st on iPhone 13",
    "typeOfApp": "Banking"
  },
  {
    "type": "resolved",
    "id": 65,
    "time": 1,
    "detectionUuid": "16a9d528-fb60-4ec3-8891-7b99df18f914"
  },
]
```
