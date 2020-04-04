#!/usr/bin/env bash
chmod +x ./gradlew
./gradlew build
./gradlew :frontend:registry-site:run
./gradlew :backend:user-service:overrideTestResources
./gradlew :backend:user-service-db-migration:overrideTestResources
./gradlew :backend:user-service-db-migration:flywayBaseline
./gradlew :backend:user-service-db-migration:flywayMigrate
./gradlew :backend:user-service:run
./gradlew :frontend:bo-iblog-site:run