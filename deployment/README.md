## Local deployment

Deployments via kubernetes and docker-compose are available.
They can be used for local environments and production like environments, but there is no guarantee for either of them.

A completely local environment without docker-compose or kubernetes can be reached when deploying the necessary docker container directly.
This can be done with:
`docker run -d -e POSTGRES_PASSWORD=abstracto -e POSTGRES_USER=abstracto -p 5432:5432 --name crimson_local crimson_database`

Then, to deploy the initial state, after executing `mvn clean install` in `image-packaging`, with:
`docker run -e DB_PASS=abstracto -e DB_PORT=5432 -e DB_USER=abstracto -e DB_NAME=abstracto -e EXECUTE_DEPLOYMENT=true -e EXECUTE_LIQUIBASE=true -e EXECUTE_TEMPLATES=true -e DB_HOST=localhost --net="host" crimson_deployment`

Afterwards you can execute `Application.java` with the token as environment variable and with the local profile (can be done via `-Dspring.profiles.active=local` for the VM options).
