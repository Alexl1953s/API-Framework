package practice;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class RestAssuredDemo {

    public static void main(String[] args) {

        //before we can successfully hit the endpoint
        //we need to have the full url
        //the method type
        //and payload

        //we are all hitting our local instances of the app under test(FOOD DELIVERY)
        String url = "http://localhost:8083/food/cache/add";
                     //localhost:8083 -> apple.applepay.qa.transit.com/food/cache/add


        //test data - payload is the test data.
        String payloadBody = "{\n" +
                "    \"foodType\": \"MainDish\",\n" +
                "    \"description\": \"Turkish Adana Kebabs\",\n" +
                "    \"imageUrl\": \"https:foods.com\",\n" +
                "    \"price\": \"12.00\",\n" +
                "    \"name\": \"Delicious Meat\"\n" +
                "}";


        //Rest Assured is one the most popular java http clients.
        //when testing apis you are really testing the servers.
        //to test the server api you have to mimic the client either via postman or automation.

        Response response = RestAssured
                //after given we provide headers and url.
                .given()
                //full url
                .baseUri(url)
                //content type is what request payload format this api expects
                .contentType(ContentType.JSON)
                //accept is what response payload format this api returns
                .accept(ContentType.JSON)
                .body(payloadBody)
                //when is the start of the action we choose the request type(GET, POST, PUT, DELETE)
                .when()
                //request return response object. Response object will have the info about status code and response payload once the api is hit.
                .request("POST");

        //response.getStatusCode() -> will return status code. 200, 300, 400, 500
        System.out.println(response.getStatusCode());

        //response.getBody() -> will returns the response body.
        System.out.println(response.getBody().asPrettyString());



        String listUrl = "http://localhost:8083/food/cache/list";

        Response foodListResponse = RestAssured
                .given()
                .baseUri(listUrl)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .request("GET");

        System.out.println(foodListResponse.getStatusCode());
        System.out.println(foodListResponse.getBody().asPrettyString());



        ////Detele

        Response response2 = RestAssured
                //after given we provide headers and url.
                .given()
                //full url
                .baseUri(url)
                //content type is what request payload format this api expects
                .contentType(ContentType.JSON)
                //accept is what response payload format this api returns
                .accept(ContentType.JSON)
                .body(payloadBody)
                //when is the start of the action we choose the request type(GET, POST, PUT, DELETE)
                .when()
                //request return response object. Response object will have the info about status code and response payload once the api is hit.
                .request("DELETE");


    }
}
