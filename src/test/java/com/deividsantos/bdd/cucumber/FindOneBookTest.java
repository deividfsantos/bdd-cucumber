package com.deividsantos.bdd.cucumber;

import com.deividsantos.bdd.TestConfig;
import com.deividsantos.bdd.dto.Book;
import com.fasterxml.jackson.databind.ObjectMapper;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;

public class FindOneBookTest extends TestConfig {

    @Autowired
    private ObjectMapper objectMapper;

    private CloseableHttpClient closeableHttpClient;

    private Book book;

    @Given("^a running application$")
    public void a_running_application() throws Throwable {
        closeableHttpClient = HttpClients.createDefault();
    }

    @When("^I access the endpoint \"([^\"]*)\" by GET$")
    public void i_access_the_endpoint_by(String endpoint) throws Throwable {
        HttpGet getRequest = new HttpGet("http://localhost:8081" + endpoint);
        getRequest.addHeader("content-type", "application/json");
        HttpResponse response = closeableHttpClient.execute(getRequest);
        book = objectMapper.readValue(response.getEntity().getContent(), Book.class);
    }

    @Then("^should return the book with the name \"([^\"]*)\" that has \"([^\"]*)\" pages$")
    public void should_return_the_book_with_the_name_that_has_pages(String name, Integer pages) throws Throwable {
        assertEquals(name, book.getName());
        assertEquals(pages, book.getPages());
    }

    @Then("^should return that the book is of the genre of \"([^\"]*)\" and the author is \"([^\"]*)\"\"$")
    public void should_return_that_the_book_is_of_the_genre_of_Comic_Book_and_the_author_is(String genre, String author) throws Throwable {
        assertEquals(genre, book.getGenre());
        assertEquals(author, book.getAuthor());
    }

    @Then("^should return that the book is of the genre of \"([^\"]*)\" and the author is \"([^\"]*)\"$")
    public void should_return_that_the_book_is_of_the_genre_of_and_the_author_is(String genre, String author) throws Throwable {
        assertEquals(genre, book.getGenre());
        assertEquals(author, book.getAuthor());
    }
}
