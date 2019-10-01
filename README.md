# Fetch Rewards Coding Assignment 

## Build the Code
```bash
$ make javaBuild
```

## Deploy to Docker Machine 
```bash
# Enable Docker Machine
$ docker-machine create default --virtualbox-memory=4096 --virtualbox-no-share
$ docker-machine start default
$ eval $(docker-machine env default) # switch to docker context
$ make docker

# Runs on your local development docker machine on port 8280, actuator endpoints are on 8281
# ex. http://localdev:8280 # localdev is the dns for your docker machine. 
# you can add this value to /etc/hosts  ex. 192.168.99.117 localdev

# Open Api can test the code from a browser (Swagger): http://localdev:8280/swagger-ui.html

# To stop the container: docker-compose stop
```