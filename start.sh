#!/usr/bin/env bash
chmod +x ./gradlew
./gradlew build
./gradlew :backend:user-service:build
./gradlew :backend:user-service:overrideDevResources
./gradlew :backend:user-service:flywayBaseline
./gradlew :backend:user-service:flywayMigrate
./gradlew :backend:user-service:run