#!/bin/bash

docker run --detach \
  --name postgres-db \
  --env POSTGRES_PASSWORD=iot_server \
  --env POSTGRES_USER=iot_server \
  --publish 5432:5432 \
  postgres:9.4.4