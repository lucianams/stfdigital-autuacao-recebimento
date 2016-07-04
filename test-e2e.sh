#!/usr/bin/env bash

MAIN_DOCKER_COMPOSE_FILE="-f docker-compose.yml"
COMPOSE_FILES_PARAMS="$MAIN_DOCKER_COMPOSE_FILE -f docker-compose-e2e.yml"

wait

docker-compose $COMPOSE_FILES_PARAMS up -d

./wait-up.sh "https://docker:8765/recebimento/info" 600
./wait-up.sh "https://docker:8765/documents/info" 30
./wait-up.sh "https://docker/OnlineEditorsExample/" 30
./wait-up.sh "http://docker:4444/wd/hub" 30

docker stats --no-stream

gradle gulpTestE2E

docker-compose $COMPOSE_FILES_PARAMS down