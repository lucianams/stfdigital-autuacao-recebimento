#!/usr/bin/env bash
set -e

./shared/scripts/wait-up.sh "https://docker:8765/recebimento/manage/info" 600