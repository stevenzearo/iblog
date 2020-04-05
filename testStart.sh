#!/usr/bin/env bash
chmod +x ./gradlew
./gradlew :backend:user-service:overrideTestResources
./gradlew :backend:user-service-db-migration:overrideTestResources
./gradlew :frontend:bo-iblog-site:overrideTestResources
./gradlew :frontend:registry-site:overrideTestResources

./gradlew :backend:user-service-db-migration:flywayMigrate

./gradlew build

./gradlew :frontend:registry-site:distribution:distTar
./gradlew :backend:user-service:distribution:distTar
./gradlew :backend:bo-iblog-site:distribution:distTar

mkdir -p 'package/backend/user-service'
mkdir -p 'package/frontend/registry-site'
mkdir -p 'package/frontend/bo-iblog-site'

tar -xvf ./frontend/registry-site/build/distribution/registry-site-1.0-SNAPSHOT.tar
tar -xvf ./backend/user-service/build/distribution/user-service-1.0-SNAPSHOT.tar
tar -xvf ./frontend/bo-iblog-site/build/distribution/bo-iblog-site-1.0-SNAPSHOT.tar

mv user-service-1.0-SNAPSHOT package/backend/user-service
mv registry-site-1.0-SNAPSHOT package/frontend/registry-site
mv bo-iblog-site-1.0-SNAPSHOT package/frontend/bo-iblog-site

chmod +x ./package/frontend/registry-site/registry-site-1.0-SNAPSHOT/bin/registry-site
nohup ./package/frontend/registry-site/bin/registry-site &

chmod +x ./package/backend/user-service/user-service-1.0-SNAPSHOT/bin/registry-site
nohup ./package/frontend/registry-site/user-service-1.0-SNAPSHOT/bin/registry-site &


chmod +x ./package/frontend/bo-iblog-site/bo-iblog-site-1.0-SNAPSHOT/bin/registry-site
nohup ./package/frontend/bo-iblog-site/bo-iblog-site-1.0-SNAPSHOT/bin/registry-site &