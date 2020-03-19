#!/usr/bin/env bash
chmod +x ./gradlew
./gradlew build
./gradlew :backend:user-service:build
./gradlew :backend:user-service:overrideDevResources
./gradlew :backend:user-service:run