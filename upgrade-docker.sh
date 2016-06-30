#!/usr/bin/env bash

# list docker-engine versions
apt-cache madison docker-engine

# upgrade docker-engine to specific version
sudo apt-get -o Dpkg::Options::="--force-confnew" install -y docker-engine=${DOCKER_VERSION}

# reinstall docker-compose at specific version
sudo rm /usr/local/bin/docker-compose
curl -L https://github.com/docker/compose/releases/download/${DOCKER_COMPOSE_VERSION}/docker-compose-`uname -s`-`uname -m` > docker-compose
chmod +x docker-compose
sudo mv docker-compose /usr/local/bin

docker version
docker-compose version