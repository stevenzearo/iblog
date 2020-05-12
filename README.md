# IBlog Project Introduce
This is a demo project of spring cloud
## Project Structure Introduce


### Gateways
1. name end with '-site'
2. controlling access of services invoke
3. dispatch services invoke

### Services
1. name end with '-service'
2. business implements

### Interfaces
1. name end with '-service-interface'
2. Interfaces of services

### Migrations
1. name end with '-service-migration'
2. database migration for services

### Registry
1. name 'registry-site'
2. service for services and gateways registry
3. service for gateways invoke services
4. site for monitor health of gateways and services
 
