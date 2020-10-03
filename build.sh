#!/bin/bash

mvn clean package

docker build -t albihasani94/threatfabric-challenge:latest .

docker-compose up
