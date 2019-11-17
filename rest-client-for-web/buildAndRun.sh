#!/bin/sh
mvn clean package && docker build -t com.airhacks/rest-client-for-web .
docker rm -f rest-client-for-web || true && docker run -d -p 8080:8080 -p 4848:4848 --name rest-client-for-web com.airhacks/rest-client-for-web 
