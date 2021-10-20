Application Name: Recipe API

Description : recipe-api project for recipe management.

Recipe CRUD API implemented using Spring Boot, Java and H2 DB.

Technologies Used:

i).  Spring Boot Version : 2.5.5
ii). Java Version : 11
iii).H2 DB
iv). Postman

Swagger URL: http://localhost:8090/swagger-ui.html

Swagger API_Doc URL: http://localhost:8090/v2/api-docs

H2 Console: http://localhost:8090/h2-console/login.jsp

JDBC URL: jdbc:h2:mem:h2db

DB Name : h2db

Tables: 

a). recipe_users
b). ingredient
c). recipe

Authentication Endpoint :

1. For user authenticatation and generate jwt token.

API Endpoint1: localhost:8090/authenticate

Method : POST

User Details:

"username": "admin",
"password": "pass"

Generate jwt token and use the generated token to call recipe endpoints. 

Recipe Endpoints:

2.For getting all the recipe details.

API Endpoint2: http://localhost:8090/recipes/

Method : GET


3. For getting single recipe details by specific recipe id.

API Endpoint3: http://localhost:8090/recipe/5

Method : GET


4. For CREATE new recipe details.

API Endpoint4: http://localhost:8090/recipe/

Method : POST

Body: 

{
    "name": "Egg Masala",
    "numberOfPeople": 5,
    "isVegetarian": true,
    "ingredientList": [
        {
            "name": "Eggs"
        },
        {
            "name": "Onions"
        },
        {
            "name": "Chilly"
        },
        {
            "name": "Masala"
        },
        {
            "name": "Oil"
        }
    ],
    "cookingInstructions": "Heat oil in a large pan over medium heat and add onions, chillies, eggs, and masala in hot oil until onions are translucent, about 10 minutes"
}


5. For UPDATE an existing recipe details.

API Endpoint5: http://localhost:8090/recipe/1

Method : PUT

Body: 

{
    "name": "Egg Masala With Rice",
    "numberOfPeople": 2,
    "isVegetarian": false,
    "ingredientList": [
        {
            "name": "Egg"
        },
        {
            "name": "Onions"
        },
        {
            "name": "Chilly"
        },
        {
            "name": "Masala"
        },
        {
            "name": "Oil"
        },
        {
            "name": "Rice"
        }
    ],
    "cookingInstructions": "Heat oil in a large pan over medium heat and add onions, chillies, eggs, and masala in hot oil until onions are translucent, about 10 minutes and mix with rice"
}

6. For DELETE a existing recipe details.

API Endpoint6 : http://localhost:8090/recipe/1

Method : DELETE

