@echo off
call mvn clean package
call docker build -t library/storage .
call docker rm -f Storage
call docker run -d -p 8002:8002 --name Storage library/storage