#!/bin/bash

mvn clean package

docker build -t threatfabric-challenge:0.0.1 .

docker-compose up
