package step_defs;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;

public class BaseDigitalBankSteps {
    private static String authenticationUri = "http://3.129.60.236:8080/bank/api/v1/auth";

    protected static String authString;
    protected static Response response;

    public static String the_admin_user_is_authenticated() {
        //
        Response authResponse = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .baseUri(authenticationUri)
                .queryParam("username", "admin@demo.io")
                .queryParam("password", "Demo123!")
                .when()
                .request("POST");

        authString = authResponse.getBody().jsonPath().getString("authToken");
        return authString;
    }




}
