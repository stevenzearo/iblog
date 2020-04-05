#!/usr/bin/env bash
chmod +x ./gradlew
./gradlew build
./gradlew :backend:user-service:overrideDevResources
./gradlew :backend:user-service-db-migration:overrideDevResources
./gradlew :backend:user-service-db-migration:flywayMigrate
nohup ./gradlew :frontend:registry-site:run &;nohup ./gradlew :backend:user-service:run &;nohup ./gradlew :frontend:bo-iblog-site:run &