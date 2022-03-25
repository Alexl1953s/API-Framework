package practice;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;

public class PracticePost {


    public static void main(String[] args) {
        String url = "http://3.129.60.236:8080/bank/api/v1/user/850/account";

        String body = "{\n" +
                "  \"accountName\": \"Askar Checking 2\",\n" +
                "  \"accountTypeCode\": \"SCK\",\n" +
                "  \"openingDeposit\": 25,\n" +
                "  \"ownerTypeCode\": \"IND\"\n" +
                "}";

        Response response = RestAssured
                .given()
                .baseUri(url)
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbkBkZW1vLmlvIiwiYXV0aCI6W3siYXV0aG9yaXR5IjoiUk9MRV9BUEkifSx7ImF1dGhvcml0eSI6IlJPTEVfQURNSU4ifV0sImlhdCI6MTY0NzU2ODE2MSwiZXhwIjoxNjQ3NTcxNzYxfQ.wqycdmAf64JfRaDW2e5Lhe-Y69idSF403YshbWkYSDk")
                .body(body)
                .when()
                .request("POST");


        System.out.println(response.getBody().asPrettyString());
        Assert.assertEquals(200, response.getStatusCode());
    }
}
