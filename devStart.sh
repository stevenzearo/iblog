#!/usr/bin/env bash
chmod +x ./gradlew
./gradlew build
./gradlew :backend:user-service:build
./gradlew :backend:user-service:overrideDevResources
./gradlew :backend:user-service-db-migration:overrideDevResources
./gradlew :backend:user-service-db-migration:flywayBaseline
./gradlew :backend:user-service-db-migration:flywayMigrate
./gradlew :backend:user-service:run