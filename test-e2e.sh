#!/usr/bin/env bash
set -e

MAIN_DOCKER_COMPOSE_FILE="-f docker-compose.yml"
COMPOSE_FILES_PARAMS="$MAIN_DOCKER_COMPOSE_FILE -f docker-compose-e2e.yml"

docker run -d --name=grid -p 4444:24444 -p 5900:25900 -e VNC_PASSWORD=hola -v /dev/shm:/dev/shm elgalu/selenium:2.53.0t
docker exec grid wait_all_done 30s

docker-compose $COMPOSE_FILES_PARAMS up -d
./wait-up.sh "https://docker:8765/recebimento/info" 600

gradle gulpTestE2E

docker-compose $COMPOSE_FILES_PARAMS down
docker stop grid