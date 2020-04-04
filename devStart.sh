#!/usr/bin/env bash
chmod +x ./gradlew
./gradlew build
setside ./gradlew :frontend:registry-site:run
./gradlew :backend:user-service:overrideDevResources
./gradlew :backend:user-service-db-migration:overrideDevResources
./gradlew :backend:user-service-db-migration:flywayBaseline
./gradlew :backend:user-service-db-migration:flywayMigrate
setside ./gradlew :backend:user-service:run
setside ./gradlew :frontend:bo-iblog-site:run