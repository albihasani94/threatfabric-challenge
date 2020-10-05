#!/bin/bash

# Prefer a local maven build to a build via the Maven project docker image because of these reasons:
# 1. Simplicity to naturally cache m2 dependencies; missing on recent versions of docker
# 2. Docker-in-docker problem. While in the docker context, you need another docker context to run testcontainers.
# (2) Can be solved by Docker wormhole, where you share the docker context between host and container; not used here
mvn clean package

# If maven command was successful
STATUS=$?
if [ $STATUS -eq 0 ]; then
  ## Build a docker image out of the output jar from the maven build process
  docker build -t albihasani94/threatfabric-challenge:local .

  ## Compose a container out of this docker image along a postgres container, configured to work together
  docker-compose up --build
fi
