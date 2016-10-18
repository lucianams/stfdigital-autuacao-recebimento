#!/usr/bin/env bash
set -e

MAIN_DOCKER_COMPOSE_FILE="-f docker-compose${1:-}.yml"
RUNNING_HOST="${2:-docker}"
COMPOSE_FILES_PARAMS="$MAIN_DOCKER_COMPOSE_FILE -f shared/compose/docker-compose.e2e.base.yml -f compose/docker-compose.e2e.yml"

EUREKA_PREFER_IP_ADDRESS=false docker-compose $COMPOSE_FILES_PARAMS up -d

./shared/scripts/wait-base.sh $RUNNING_HOST
./scripts/wait-ready.sh $RUNNING_HOST
./shared/scripts/wait-up.sh "http://$RUNNING_HOST:4444/wd/hub" 30

gradle gulpTestE2E