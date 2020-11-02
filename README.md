# Lunch Microservice

The service provides an endpoint that will determine, from a set of recipes, what I can have for lunch at a given date, based on my fridge ingredient's expiry date, so that I can quickly decide what I’ll be having to eat, and the ingredients required to prepare the meal.

## Prerequisites

* [Docker](https://docs.docker.com/get-docker/) & [Docker-Compose](https://docs.docker.com/compose/install/)
* [Java 11 Runtime](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)

### Run

1. Start database:
    ```
    docker-compose up -d
    ```
   
1. Add test data from  `sql/lunch-data.sql` to the database. Here's a helper script if you prefer:

    ```bash
   CONTAINER_ID=$(docker inspect --format="{{.Id}}" lunch-db)
   
   docker cp sql/lunch-data.sql $CONTAINER_ID:/lunch-data.sql
   
   docker exec $CONTAINER_ID /bin/sh -c 'mysql -u root -prezdytechtask lunch </lunch-data.sql'
   ```

1. Run Springboot LunchApplication
