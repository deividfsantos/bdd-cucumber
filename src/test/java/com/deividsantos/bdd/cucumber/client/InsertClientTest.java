package com.deividsantos.bdd.cucumber.client;

import com.deividsantos.bdd.TestConfig;
import com.deividsantos.bdd.input.ClientInput;
import com.deividsantos.bdd.output.ClientOutput;
import com.fasterxml.jackson.databind.ObjectMapper;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import gherkin.deps.com.google.gson.Gson;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class InsertClientTest extends TestConfig {

    @Autowired
    private ObjectMapper objectMapper;

    private CloseableHttpClient closeableHttpClient;

    private ClientInput clientInput;

    private HttpPost postRequest;

    @Before
    public void setup() {
        clientInput = new ClientInput();
        closeableHttpClient = HttpClients.createDefault();

    }

    @Given("^I want to save the client \"([^\"]*)\" with the phone \"([^\"]*)\"$")
    public void i_want_to_save_the_client_with_the_phone(String name, String phone) {
        clientInput.setName(name);
        clientInput.setPhone(phone);
    }

    @When("^I access the endpoint \"([^\"]*)\" by POST with this client$")
    public void i_access_the_endpoint_by_POST_with_this_client(String endpoint) throws IOException {
        postRequest = new HttpPost("http://localhost:8081" + endpoint);
        postRequest.addHeader("content-type", "application/json");
        postRequest.setEntity(new StringEntity(new Gson().toJson(clientInput)));
    }

    @Then("^should save the client and return \"([^\"]*)\"$")
    public void shouldSaveTheClientAndReturn(int returnCode) throws Throwable {
        HttpResponse response = closeableHttpClient.execute(postRequest);
        assertEquals(returnCode, response.getStatusLine().getStatusCode());
        assertEquals(getSavedClient().getName(), clientInput.getName());
        assertEquals(getSavedClient().getPhone(), clientInput.getPhone());
    }

    public ClientOutput getSavedClient() throws IOException {
        HttpGet getRequest = new HttpGet("http://localhost:8081/v1/clients/3");
        getRequest.addHeader("content-type", "application/json");
        HttpResponse response = closeableHttpClient.execute(getRequest);
        return objectMapper.readValue(response.getEntity().getContent(), ClientOutput.class);
    }

}
