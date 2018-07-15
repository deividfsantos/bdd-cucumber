package com.deividsantos.bdd.cucumber.book;

import com.deividsantos.bdd.TestConfig;
import com.deividsantos.bdd.output.BookOutput;
import com.fasterxml.jackson.databind.ObjectMapper;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;

public class RentABookTest extends TestConfig {

    @Autowired
    private ObjectMapper objectMapper;

    private Integer bookCode;
    private Integer clientCode;
    private CloseableHttpClient closeableHttpClient;
    private HttpPost postRequest;

    @Before
    public void setup() {
        closeableHttpClient = HttpClients.createDefault();
    }

    @Given("^I want to rent the book with code (\\d+) that has (\\d+) in stock$")
    public void iWantToRentTheBookWithCode(int code, int stock) throws Throwable {
        bookCode = code;
    }

    @And("^I'm the client with the code (\\d+)$")
    public void iMTheClientWithTheCode(int code) throws Throwable {
        clientCode = code;
    }

    @When("^I rent the book by POST$")
    public void iRentTheBookByPOST() throws Throwable {
        postRequest = new HttpPost("http://localhost:8081/v1/books/" + bookCode + "/rental/" + clientCode);
        postRequest.addHeader("content-type", "application/json");
    }

    @Then("^the book should be rented$")
    public void theBookShouldBeRented() throws Throwable {
        HttpResponse response = closeableHttpClient.execute(postRequest);
        assertEquals(response.getStatusLine().getStatusCode(), 200);
    }

    @And("^have reduced (\\d+) of your stock to stay with (\\d+)$")
    public void haveReducedOfYourStock(int sizeReduce, int stock) throws Throwable {
        HttpGet getRequest = new HttpGet("http://localhost:8081/v1/books/" + bookCode);
        getRequest.addHeader("content-type", "application/json");
        HttpResponse response = closeableHttpClient.execute(getRequest);
        BookOutput book = objectMapper.readValue(response.getEntity().getContent(), BookOutput.class);
        assertEquals(book.getStock(), Integer.valueOf(stock));
    }
}
