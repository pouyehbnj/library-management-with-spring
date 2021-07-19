#!/bin/sh
mvn clean package && docker build -t library/search .
docker rm -f Search || true && docker run -d -p 8001:8001 --name Search library/search