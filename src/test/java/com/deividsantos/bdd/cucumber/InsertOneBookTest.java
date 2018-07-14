package com.deividsantos.bdd.cucumber;

import com.deividsantos.bdd.TestConfig;
import com.deividsantos.bdd.dto.Book;
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
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class InsertOneBookTest extends TestConfig {

    @Autowired
    private ObjectMapper objectMapper;

    private CloseableHttpClient closeableHttpClient;

    private Book book;

    private Book expectedBook;

    private RestTemplate restTemplate;

    @Before
    public void setup() {
        book = new Book();
        closeableHttpClient = HttpClients.createDefault();
    }


    @Given("^I have the book \"([^\"]*)\"$")
    public void i_have_the_book(String bookName) {
        book.setName(bookName);
    }

    @Given("^it has \"([^\"]*)\" pages, of the genre \"([^\"]*)\" and author \"([^\"]*)\"$")
    public void it_has_pages_of_the_genre_and_author(Integer pages, String genre, String author) {
        book.setPages(pages);
        book.setGenre(genre);
        book.setAuthor(author);
    }

    @When("^I save the book in the endpoint \"([^\"]*)\" by POST$")
    public void i_save_the_book_in_the_endpoint_by_POST(String endpoint) throws IOException {
        HttpPost postRequest = new HttpPost("http://localhost:8081" + endpoint);
        postRequest.addHeader("content-type", "application/json");
        postRequest.setEntity(new StringEntity(new Gson().toJson(book)));
        HttpResponse response = closeableHttpClient.execute(postRequest);
        assertEquals(response.getStatusLine().getStatusCode(), 200);
    }

    @When("^search the book$")
    public void search_the_book() throws IOException {
        HttpGet getRequest = new HttpGet("http://localhost:8081/v1/books/4");
        getRequest.addHeader("content-type", "application/json");
        HttpResponse response = closeableHttpClient.execute(getRequest);
        expectedBook = objectMapper.readValue(response.getEntity().getContent(), Book.class);
    }

    @Then("^should return all data from the book \"([^\"]*)\"$")
    public void should_return_all_data_from_the_book(String name) {
        assertEquals(expectedBook.getName(), name);
        assertEquals(expectedBook.getGenre(), book.getGenre());
        assertEquals(expectedBook.getAuthor(), book.getAuthor());
        assertEquals(expectedBook.getPages(), book.getPages());
    }

}
