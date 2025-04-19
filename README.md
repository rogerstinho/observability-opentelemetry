## Prerequisites
- Maven
- Docker
- Docker Compose
- Java 17

## Architecture
![Diagram](./gif/diagram.gif)

## Build the project
```bash 
mvn clean package
```

## Install Opentelemetry workshop and Run the project
1 - Create the docker network
```bash 
docker network create observability-network
```

2 - Run opentelemetry docker compose
```bash
docker-compose -f otel-docker-compose.yml up -d
```
3 - Run the microservices docker compose
```bash
docker-compose -f docker-compose.yml up -d
```

4 - Check the Grafana UI
```bash
http://localhost:3000
```
5 - Test the API with the bruno collection

