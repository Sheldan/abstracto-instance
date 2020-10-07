## Install instructions

Build project with `docker-offline` maven profile (or `docker` if docker login has been executed).
* Take the created `docker-config-$version-docker-compose.zip` from the target folder and extract it wherever you want.
* Set the necessary variables on the shell (TOKEN, DATABASE_PASSWORD) to the appropriate values
 * Execute `docker-compose up`