@echo off
call mvn clean package
call docker build -t library/search .
call docker rm -f Search
call docker run -d -p 8001:8001 --name Search library/search