#!/bin/sh
mvn clean package && docker build -t com.mycompany/SpringTest .
docker rm -f SpringTest || true && docker run -d -p 9080:9080 -p 9443:9443 --name SpringTest com.mycompany/SpringTest