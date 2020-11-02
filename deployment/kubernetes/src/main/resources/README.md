# Setup for local kubernetes deployment
 * Install docker from [here](https://docs.docker.com/get-docker/).
 * Install microk8s from [here](https://microk8s.io/) via `sudo snap install microk8s --classic` and create an alias for `microk8s.kubectl`
 * `sudo iptables -P FORWARD ACCEPT` to fix issue with `1 pod has unbound immediate PersistentVolumeClaims.` which happened to me after resetting the cluster.
 * Add `{ "insecure-registries" : ["localhost:32000"] }` to `/etc/docker/daemon.json` and restart docker
 * Enable the plugins `dashboard`, `registry`, `dns` and `host-access` with `microk8s enable dashboard dns registry host-access`.
 * The configuration does not contain its own postgres database. It is required to install it separately. There is a DockerFile available in `image-packaging`.
 * Replace the configuration in `database-secrets.yaml` with the base64 representation of the required values (host, password, etc.)
 * Replace the configuration in `discord-secrets.yaml` with the base64 representation of the required value (Token)
 * Replace the configuration in `database-config.yaml` with the required configuration (username, port and database name)
 * Execute `docker-compose build && docker-compose push` in the `image packaging` module in order to get the images on the local repository. In order to push to the local repository you need to change the image names in `image-packaging` to refer to the local repository.
    You can do so by prefixing `localhost:32000/` to the image names, you also might need to do so for the original deployment container provided by Abstracto.
 * If you want to use pgAdmin you need to change the configuration in `servers.json` in the pgAdmin container config in `image-packaging` to `10.0.1.1` if you host the database on your local machine. This IP address comes from the host-access plugin.
 * Execute the following commands in this folder for setting up the cluster:
   * `$alias apply -f crimson-namespace.yaml` To create the namespace we are going to use
   * `$alias apply -f database-secrets.yaml -n crimson`
   * `$alias apply -f database-config.yaml -n crimson`
   * `$alias apply -f discord-secrets.yaml -n crimson`
   * `$alias apply -f pgAdmin-deployment.yaml -n crimson` To create the container for pgAdmin
   * `$alias apply -f pgAdmin-service.yaml -n crimson` To make the pgAdmin available
   * `$alias apply -f database-config-job.yaml -n crimson` To deploy the database configuration
   * `$alias apply -f crimson-deployment.yaml -n crimson` To deploy the bot
After that the bot should be up and running