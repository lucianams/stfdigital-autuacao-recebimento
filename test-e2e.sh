#!/usr/bin/env bash

COMPOSE_FILES_PARAMS="-f docker-compose-e2e.yml"

docker-compose $COMPOSE_FILES_PARAMS up -d
docker-compose $COMPOSE_FILES_PARAMS down