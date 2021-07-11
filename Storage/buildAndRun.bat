@echo off
call mvn clean package
call docker build -t com.library/SpringTest .
call docker rm -f SpringTest
call docker run -d -p 9080:9080 -p 9443:9443 --name SpringTest com.library/SpringTest