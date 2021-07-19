#!/bin/sh
mvn clean package && docker build -t library/storage .
docker rm -f Storage || true && docker run -d -p 8002:8002 --name Storage library/storage