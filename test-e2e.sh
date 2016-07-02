#!/usr/bin/env bash
set -e

MAIN_DOCKER_COMPOSE_FILE="-f docker-compose.yml"
COMPOSE_FILES_PARAMS="$MAIN_DOCKER_COMPOSE_FILE -f docker-compose-e2e.yml"

docker run -d --name=selenium --net host -p 4444:4444 selenium/standalone-chrome:2.53.0

docker-compose $COMPOSE_FILES_PARAMS up -d
./wait-up.sh "https://docker:8765/recebimento/info" 600
./wait-up.sh "https://docker:8765/documents/info" 30
./wait-up.sh "https://docker/OnlineEditorsExample/" 30

free -m
df -h
docker stats --no-stream

gradle gulpTestE2E

docker-compose $COMPOSE_FILES_PARAMS down
docker stop selenium && docker rm selenium