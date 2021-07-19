#!/bin/sh
mvn clean package && docker build -t library/authentication .
docker rm -f Authentication || true && docker run -d -p 8000:8000 --name Authentication library/authentication