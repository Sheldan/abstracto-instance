# Setup for local docker-compose deployment

* Install docker [here](https://docs.docker.com/get-docker/)
* Install docker-compose [here](https://docs.docker.com/compose/install/).
* Execute `docker-compose build` in `image-packaging`.
* Fill out values in `setup_env.sh` for token and database password. These are the necessary values, the rest has default values.
* Execute `docker-compose up` in this directory and wait for completion.
* Beware that a log folder in the current directory will be created, which will require more permissions to delete.
* Per default pgAdmin is reachable on `localhost:5050` with the configured user and password. It will contain a configuration in the servers list.

You can use `disable_update.sh` and `enable_update.sh` to change the environment values responsible for deploying new versions of templates and liquibase.
When you change something in the templates you need to re-build `packaging-paacking` so the docker images are re-created.
