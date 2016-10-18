#!/usr/bin/env bash
set -e

MAIN_DOCKER_COMPOSE_FILE="-f docker-compose${1:-}.yml"
RUNNING_HOST="${2:-docker}"
COMPOSE_FILES_PARAMS="$MAIN_DOCKER_COMPOSE_FILE -f shared/compose/docker-compose.e2e.base.yml -f compose/docker-compose.e2e.yml"

docker-compose $COMPOSE_FILES_PARAMS down