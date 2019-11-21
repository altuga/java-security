# Build
mvn clean package && docker build -t com.airhacks/cryptographic .

# RUN

docker rm -f cryptographic || true && docker run -d -p 8080:8080 -p 4848:4848 --name cryptographic com.airhacks/cryptographic 