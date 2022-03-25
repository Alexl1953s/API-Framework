# APIGroupProject3
# FOOD DELIVERY API 
## _FOOD_ functionality 
**TODO:**
`Test all functionalities based on the requirements below.
 Manually test each requirement in Postman, validate API Responses 
 and records in Database(food_delivery_db1 schema)
`
####Functionality 1. 
####TODO: Write test cases(scenarios) and execute them manually. Validate each enpoint. Check logs for each enpoint as well. 
User should be able to ADD food to cache, to validate the current cache data send request to "food/cache/list": 
 API Endpoint: 
```json
  http://localhost:8083/food/cache/add
```
   Fields: 
 - description (not required)
 - imageUrl (required)
 - price(required)
 - name(required)
 - foodType(required)
 -----------
 If User adds food without required fields, user should see the following messages: 
 1. Invalid request - Food image url cannot be null or empty.
 2. Invalid request - Food price cannot be null or empty.
 3. Invalid request - Food name cannot be null or empty.
 4. Invalid request - Food type cannot be null or empty.
> Note: 
> Food Type can only be:     
        Beverages,
        Appetizers,
        MainDish.
>
If User adds food with invalid food type then user should see the error message.
 See following example to test manually:
 
 Allowed HTTPs request to add food:
  
  **`POST`**
 
 Request body:
 ```json
{
    "description":"Wine",
    "imageUrl": "https:foods.com",
    "price": "20.00",
    "name": "Merlot",
    "foodType": "Beverages"
}
```
##### Scenario 1:
```gherkin
Given add new food to FoodDelivery with the following fields
|description|imageUrl       | price   |  name     |  foodType   |
|Wine       |https:foods.com| 20.00   |  Merlot   |  Beverages  |
Then verify that status code is 200
Then verify that food has been successfully added
```
Example response body: 
```json
{
    "foodCached": [
        {
            "description": "Merlot",
            "imageUrl": "https:foods.com",
            "price": 20.00,
            "name": "Merlot",
            "foodType": "Beverages"
        }
    ]
}
```
##### Scenario 2:
```gherkin
Given add new food to FoodDelivery without image url
|description| price   |  name     |  foodType   |
|Wine       | 20.00   |  Merlot   |  Beverages  |
Then verify that status code is 403
Then verify response error message "Invalid request - Food image url cannot be null or empty." 
```
Example response body: 
```json
{
    "errorMessage": "Invalid request - Food image url cannot be null or empty."
}
```
##### Scenario 3:
```gherkin
Given add new food to FoodDelivery without price
|description| imageUrl        |  name     |  foodType   |
|Wine       |https:foods.com  |  Merlot   |  Beverages  |
Then verify that status code is 403
Then verify response error message "Invalid request - Food price cannot be null or empty." 
```
Example response body: 
```json
{
    "errorMessage": "Invalid request - Food price cannot be null or empty."
}
```
##### Scenario 4:
```gherkin
Given add new food to FoodDelivery without name
|description| imageUrl        |  price     |  foodType   |
|Wine       |https:foods.com  |  20.00     |  Beverages  |
Then verify that status code is 403
Then verify response error message "Invalid request - Food name cannot be null or empty." 
```
Example response body: 
```json
{
    "errorMessage": "Invalid request - Food name cannot be null or empty."
}
```
##### Scenario 5:
```gherkin
Given add new food to FoodDelivery without food type
|description| imageUrl        |  name     |  price   |
|Wine       |https:foods.com  |  Merlot   |  20.00   |
Then verify that status code is 403
Then verify response error message "Invalid request - Food type cannot be null or empty." 
```
Example response body: 
```json
{
    "errorMessage": "Invalid request - Food type cannot be null or empty."
}
```
##### Scenario 6:
```gherkin
Given add new food to FoodDelivery with invalid food type
|description| imageUrl        |  name     |  price   |foodType|
|Wine       |https:foods.com  |  Merlot   |  20.00   | Soups  |
Then verify that status code is 400
Then verify response error message "Bad Request" 
```
Example response body: 
```json
{
    "timestamp": "2020-09-10T15:04:57.408+0000",
    "status": 400,
    "error": "Bad Request",
    "message": "JSON parse error: Cannot deserialize value of type `com.devxschool.food_delivery.models.Food$FoodType` from String \"Soups\": not one of the values accepted for Enum class: [MainDish, Beverages, Appetizers]; nested exception is com.fasterxml.jackson.databind.exc.InvalidFormatException: Cannot deserialize value of type `com.devxschool.food_delivery.models.Food$FoodType` from String \"Soups\": not one of the values accepted for Enum class: [MainDish, Beverages, Appetizers]\n at [Source: (PushbackInputStream); line: 6, column: 14] (through reference chain: com.devxschool.food_delivery.models.Food[\"foodType\"])",
    "path": "/food/cache/add"
}
```
-------
####Functionality 2.
####TODO: Write test cases(scenarios) and execute them manually. Validate each enpoint. Check logs for each enpoint as well. 
 
User should be able to list all added foods in cache.
 API Endpoint: 
```json
  http://localhost:8083/food/cache/list
```
 Allowed HTTPs request to list food:
  **`GET`**
 
##### Scenario 1:
```gherkin
Given user list all food in cache
Then verify that status code is 200
Then verify that response contains all cached foods
```
Example response body: NOTE: The response will be based on what type and how many foods user have added
```json
{
    "numberOfFoodsInCache": 3,
    "numberOfAppetizers": 1,
    "numberOfMainDishes": 1,
    "numberOfUnknownFood": 1,
    "foodCached": [
        {
            "description": "Merlot",
            "imageUrl": "https:foods.com",
            "price": 20.00,
            "name": "Merlot",
            "foodType": "Beverages"
        },
        {
            "description": "Turkish Baklava",
            "imageUrl": "https:foods.com",
            "price": 10.00,
            "name": "Baklava",
            "foodType": "Appetizers"
        },
        {
            "description": "Smoked Salmon",
            "imageUrl": "https:foods.com",
            "price": 30.00,
            "name": "Smoked Salmon",
            "foodType": "MainDish"
        }
    ]
}
```
------
####Functionality 3. 
####TODO: Write test cases(scenarios) and execute them manually. Validate each enpoint. Check logs for each enpoint as well. 
User should be able to update food based on the provided name and field to update
Note: Price's max limit is $125.00
 API Endpoint: 
```json
http://localhost:8083/food/cache/update?name={food name}&field={fieldName}
```
 Allowed HTTPs request to update food:
  **`PUT`**
 
##### Scenario 1:
```gherkin
Given add new food to FoodDelivery with the following fields
|description|imageUrl       | price   |  name     |  foodType   |
|Wine       |https:foods.com| 20.00   |  Merlot   |  Beverages  |
And verify that status code is 200
And verify that food has been successfully added
When user updates "Merlot"'s price to 100.00 
Then verify that status code is 200
Then verify that price have been updated
```
Example Request body: 
```json
{
    "description":"Steak",
    "imageUrl": "https:foods.com",
    "price": "100.00",
    "name": "T-Bone steak",
    "foodType": "MainDish"
}
```
Example Response body:
```json
{
    "foodCached": [
        {
            "description": "Steak",
            "imageUrl": "https:foods.com",
            "price": 100.00,
            "name": "T-Bone steak",
            "foodType": "MainDish"
        }
    ]
}
```
##### Scenario 2: 
```gherkin
Given user updates "T-Bone steak"'s price to 125.50 
Then verify that status code is 403
Then verify that error message "Invalid request - Food price should be kept less than 125" is displayed
Then verify 
```
Example response body: 
```json
{
   
    "description":"Steak",
    "imageUrl": "https:foods.com",
    "price": "125.50",
    "name": "T-Bone steak",
    "foodType": "MainDish"
   
}
```
Example Request Body:
```json
{
    "errorMessage": "Invalid request - Food price should be kept less than 125"
}
```
------
####Functionality 4.
####TODO: Write test cases(scenarios) and execute them manually. Validate each enpoint.
 Check logs for each enpoint as well. Validate data in food_delivery Database.
 
User should be able to commit changes so that it saves cache to DB _food_ table and clears the cache data.
Some food can be excluded from being saved in DB.
 API Endpoint: 
```json
http://localhost:8083/food/commit
http://localhost:8083/food/commit?exclude={food name}
```
 Allowed HTTPs request to register a user:
  **`POST`**
 
##### Scenario 1:
```gherkin
Given user saves all chached food
Then verify that status code is 200
Then verify number of saved food
Then verify response message "Food Cache is committed to db"
Then verify that all food information is saved in DB
Then verify that food cache is empty
```
##### Scenario 2:
```gherkin
Given user saves all chached food excluding "Diet Coke"
Then verify that status code is 200
Then verify number of saved food
Then verify response message "Food Cache is committed to db"
Then verify that all food information is saved in DB
Then verify that food cache is empty
```
Example response body: 
```json
{
    "numberOfFoodsSaved": 3,
    "message": "Food Cache is committed to db"
}
```
-------