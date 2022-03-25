package step_defs;

import com.fasterxml.jackson.databind.ObjectMapper;
import cucumber.api.DataTable;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import domains.Transaction;
import domains.TransactionResponse;
import domains.TransactionType;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;

import java.util.List;

public class TransactionsSteps extends BaseDigitalBankSteps {

    ObjectMapper mapper = new ObjectMapper();
    TransactionResponse[] actualResponseArray;

    @When("^I send the following new transaction payload with the checking account id '(\\d+)'$")
    public void i_send_the_following_new_transaction_payload_with_the_checking_account_id(int checkingAccountId, List<Transaction> transactionsList) throws Throwable {
        String json = mapper.writeValueAsString(transactionsList.get(0));
        System.out.println(json);


        String url = "http://3.129.60.236:8080/bank/api/v1/account/" + checkingAccountId + "/transaction";
        response = RestAssured
                .given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + the_admin_user_is_authenticated())
                .body(json)
                .baseUri(url)
                .when()
                .post();

    }

    @Then("^the following should be the transaction response payload$")
    public void the_following_should_be_the_transaction_response_payload(List<Transaction> expectedTransactionsList) throws Throwable {
        System.out.println(response.getBody().asPrettyString());

        //object mapper class is class from a jackson
        // library that we use to serialize and deserialize json to java object and vice versa

        //readValue method is used to serialize from json to java
        //first param is the json in String.
        //second param is which class it should be serialized to
        actualResponseArray = mapper.readValue(response.getBody().asString(), TransactionResponse[].class);

        System.out.println(expectedTransactionsList.get(0).getDescription());
        System.out.println(actualResponseArray[0].getDescription());
        Assert.assertEquals(expectedTransactionsList.get(0).getDescription(), actualResponseArray[0].getDescription());

        //delta - difference
        Assert.assertEquals(expectedTransactionsList.get(0).getAmount(), actualResponseArray[0].getAmount(), 0);


    }

    @Then("^the following should be the transaction type response payload$")
    public void the_following_should_be_the_transaction_type_response_payload(List<TransactionType> transactionTypes) throws Throwable {
        System.out.println(transactionTypes.get(0).getCode());

        Assert.assertEquals(transactionTypes.get(0).getCode(), actualResponseArray[0].getTransactionType().getCode());
        Assert.assertEquals(transactionTypes.get(0).getName(), actualResponseArray[0].getTransactionType().getName());
        Assert.assertEquals(transactionTypes.get(0).getCategory(), actualResponseArray[0].getTransactionType().getCategory());

    }

    @Then("^the following should be the transaction state response payload$")
    public void the_following_should_be_the_transaction_state_response_payload(DataTable arg1) throws Throwable {

    }

    @Then("^the following should be the transaction category response payload$")
    public void the_following_should_be_the_transaction_category_response_payload(DataTable arg1) throws Throwable {

    }
}



