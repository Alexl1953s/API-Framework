package practice;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;

public class Practice2 {

    public static void main(String[] args) {


        String baseUri = "http://3.129.60.236:8080/bank/api/v1/users";

        Response response = RestAssured
                .given()
                .accept(ContentType.JSON)
                .baseUri(baseUri)
                .header("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbkBkZW1vLmlvIiwiYXV0aCI6W3siYXV0aG9yaXR5IjoiUk9MRV9BUEkifSx7ImF1dGhvcml0eSI6IlJPTEVfQURNSU4ifV0sImlhdCI6MTY0NzU2NDQ2OSwiZXhwIjoxNjQ3NTY4MDY5fQ.LYiTOC7nK2gOXGIyzWiaUjVdYmVeWckjGjhpZT8oAv8")
                .when()
                .request("get");


        Assert.assertEquals("Get all users endpoint invalid status code", 200, response.getStatusCode());
        System.out.println(response.getBody().asPrettyString());
    }



}
