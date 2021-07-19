@echo off
call mvn clean package
call docker build -t library/authentication .
call docker rm -f Authentication
call docker run -d -p 8000:8000 --name Authentication library/authentication