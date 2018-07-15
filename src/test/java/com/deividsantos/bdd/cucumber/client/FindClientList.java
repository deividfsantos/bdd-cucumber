package com.deividsantos.bdd.cucumber.client;

import com.deividsantos.bdd.TestConfig;
import com.deividsantos.bdd.output.ClientOutput;
import com.fasterxml.jackson.databind.ObjectMapper;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class FindClientList extends TestConfig {

    @Autowired
    private ObjectMapper objectMapper;

    private CloseableHttpClient closeableHttpClient;

    private List<ClientOutput> clients;

    @Before
    public void setup() {
        closeableHttpClient = HttpClients.createDefault();
    }

    @Given("^I want to find all saved clients$")
    public void i_want_to_find_all_saved_clients() {
    }

    @When("^I access API \"([^\"]*)\" with GET$")
    public void i_access_API_with_GET(String endpoint) throws IOException {
        HttpGet getRequest = new HttpGet("http://localhost:8081" + endpoint);
        getRequest.addHeader("content-type", "application/json");
        HttpResponse response = closeableHttpClient.execute(getRequest);
        clients = Arrays.asList(objectMapper.readValue(response.getEntity().getContent(), ClientOutput[].class));
    }

    @Then("^should return a list with all saved clients$")
    public void should_return_a_list_with_all_saved_clients() {
        assertEquals(clients.get(0).getName(), expectedClients().get(0).getName());
        assertEquals(clients.get(0).getPhone(), expectedClients().get(0).getPhone());

        assertEquals(clients.get(1).getName(), expectedClients().get(1).getName());
        assertEquals(clients.get(1).getPhone(), expectedClients().get(1).getPhone());
    }

    public List<ClientOutput> expectedClients() {
        return Arrays.asList(
                new ClientOutput("Deivid Santos", "51985236599"),
                new ClientOutput("Joseclilton Silva", "51963250465")
        );
    }

}
