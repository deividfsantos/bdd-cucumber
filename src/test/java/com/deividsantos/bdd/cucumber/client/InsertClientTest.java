package com.deividsantos.bdd.cucumber.client;

import com.deividsantos.bdd.TestConfig;
import com.deividsantos.bdd.input.ClientInput;
import com.deividsantos.bdd.output.ClientOutput;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.WireMockServer;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
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

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static org.junit.Assert.assertEquals;

public class InsertClientTest extends TestConfig {

    @Autowired
    private ObjectMapper objectMapper;

    private CloseableHttpClient closeableHttpClient;
    private ClientInput clientInput;
    private HttpPost postRequest;
    private HttpResponse response;
    private WireMockServer wireMockServer;

    @Before
    public void setup() {
        wireMockServer = new WireMockServer(wireMockConfig().port(8089));
        wireMockServer.start();
        wireMockServer.stubFor(get(urlEqualTo("/country/get/iso2code/BR"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "text/json")
                        .withStatus(200)
                        .withBody("{\n" +
                                "    \"RestResponse\": {\n" +
                                "        \"messages\": [\n" +
                                "            \"Country found matching code [BR].\"\n" +
                                "        ],\n" +
                                "        \"result\": {\n" +
                                "            \"name\": \"Brazil\",\n" +
                                "            \"alpha2_code\": \"BR\",\n" +
                                "            \"alpha3_code\": \"BRA\"\n" +
                                "        }\n" +
                                "    }\n" +
                                "}")));
        clientInput = new ClientInput();
        closeableHttpClient = HttpClients.createDefault();
    }

    @After
    public void end() {
        wireMockServer.stop();
    }

    @Given("^I want to save the client \"([^\"]*)\" with the phone \"([^\"]*)\"$")
    public void i_want_to_save_the_client_with_the_phone(String name, String phone) {
        clientInput.setName(name);
        clientInput.setPhone(phone);
    }

    @And("^nationality is \"([^\"]*)\"$")
    public void nationalityIs(String nationality) throws Throwable {
        clientInput.setNationality(nationality);

    }

    @When("^I access the endpoint \"([^\"]*)\" by POST with this client$")
    public void i_access_the_endpoint_by_POST_with_this_client(String endpoint) throws IOException {
        postRequest = new HttpPost("http://localhost:8081" + endpoint);
        postRequest.addHeader("content-type", "application/json");
        postRequest.setEntity(new StringEntity(new Gson().toJson(clientInput)));
    }

    @Then("^should save the client$")
    public void shouldSaveTheClient() throws Throwable {
        response = closeableHttpClient.execute(postRequest);
    }

    @And("^return (\\d+) with this client with nationality \"([^\"]*)\"$")
    public void returnWithThisClientWithNationality(int statusCode, String nationality) throws Throwable {
        assertEquals(statusCode, response.getStatusLine().getStatusCode());
        assertEquals(getSavedClient().getName(), clientInput.getName());
        assertEquals(getSavedClient().getPhone(), clientInput.getPhone());
        assertEquals(getSavedClient().getNationality(), nationality);
    }

    public ClientOutput getSavedClient() throws IOException {
        HttpGet getRequest = new HttpGet("http://localhost:8081/v1/clients/3");
        getRequest.addHeader("content-type", "application/json");
        HttpResponse response = closeableHttpClient.execute(getRequest);
        return objectMapper.readValue(response.getEntity().getContent(), ClientOutput.class);
    }


}
