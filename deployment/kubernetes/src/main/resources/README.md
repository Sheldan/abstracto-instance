# Setup for local kubernetes deployment
 * Install docker from [here](https://docs.docker.com/get-docker/).
 * Install microk8s from [here](https://microk8s.io/) via `sudo snap install microk8s --classic` and create an alias for `microk8s.kubectl`
 * `sudo iptables -P FORWARD ACCEPT` to fix issue with `1 pod has unbound immediate PersistentVolumeClaims.` which happened to me after resetting the cluster.
 * Add `{ "insecure-registries" : ["localhost:32000"] }` to `/etc/docker/daemon.json` and restart docker
 * Enable the plugins `dashboard`, `registry`, `dns` and `host-access` with `microk8s enable dashboard dns registry host-access`.
 * If the CLI is used to generate the secrets, beware to use `echo -n` in order to not have new lines.
 * Replace the configuration in `database-secrets.yaml` with the base64 representation of the required values (host, password, etc.)
 * Replace the configuration in `discord-secrets.yaml` with the base64 representation of the required value (Token)
 * Replace the configuration in `database-config.yaml` with the required configuration (username, port and database name)
 * Execute `docker-compose build && docker-compose push` in the `image packaging` module in order to get the images on the local repository. In order to push to the local repository you need to change the image names in `image-packaging` to refer to the local repository.
    You can do so by prefixing `localhost:32000/` to the image names, you also might need to do so for the original deployment container provided by Abstracto.
There are a few values which need changing when you change the name of the services or similar:
The hostname of the postgres service in `servers.json` in the pgAdmin container.
The hostname of the prometheus datasource in `all.yml` in the grafana container.
The username and password of the spring application in `crimson-secrets.yaml` needs to be in sync with the configuration in the prometheus data source in `all.yml`
 * Execute the following commands in this folder for setting up the cluster:
   * `$alias apply -f crimson-namespace.yaml` To create the namespace we are going to use
   * `$alias apply -f database-secrets.yaml -n crimson`
   * `$alias apply -f crimson-secrets.yaml -n crimson`
   * `$alias apply -f database-config.yaml -n crimson`
   * `$alias apply -f discord-secrets.yaml -n crimson`
   * `$alias apply -f postgres-deployment.yaml -n crimson`
   * `$alias apply -f postgres-service.yaml -n crimson`
   * `$alias apply -f pgAdmin-deployment.yaml -n crimson` To create the container for pgAdmin
   * `$alias apply -f pgAdmin-service.yaml -n crimson` To make the pgAdmin available
   * `$alias apply -f database-config-job.yaml -n crimson` To deploy the database configuration
   * `$alias apply -f crimson-deployment.yaml -n crimson` To deploy the bot
   * `$alias apply -f prometheus-deployment.yaml -n crimson` To create the container for prometheus
   * `$alias apply -f prometheus-service.yaml -n crimson` To make it available as a service (optional)
   * `$alias apply -f grafana-deployment.yaml -n crimson` To deploy grafana
   * `$alias apply -f grafana-service.yaml -n crimson` To make it available as a service
After that the bot should be up and running

