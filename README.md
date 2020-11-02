# Lunch Microservice

The service provides an endpoint that will determine, from a set of recipes, what I can have for lunch at a given date, based on my fridge ingredient's expiry date, so that I can quickly decide what I’ll be having to eat, and the ingredients required to prepare the meal.

## Local development

Use `docker-compose` to start local MySQL DB server and `lunch-data.sql` to initiate it with data.  

### Run

1. Start DB
    ```
    docker-compose up -d
    ```
1. Initialise test data from
    ```
   sql/lunch-data.sql
   ```
1. Run Springboot LunchApplication
