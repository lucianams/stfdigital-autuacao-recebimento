#!/usr/bin/env bash
set -e

MAIN_DOCKER_COMPOSE_FILE="-f docker-compose.yml"
COMPOSE_FILES_PARAMS="$MAIN_DOCKER_COMPOSE_FILE -f docker-compose-e2e.yml"

docker-compose $COMPOSE_FILES_PARAMS up -d
./wait-up.sh "https://localhost:8765/recebimento/info" 600
docker-compose $COMPOSE_FILES_PARAMS down