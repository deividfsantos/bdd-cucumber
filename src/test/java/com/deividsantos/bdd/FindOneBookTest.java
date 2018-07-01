package com.deividsantos.bdd;

import com.deividsantos.bdd.dto.Book;
import com.fasterxml.jackson.databind.ObjectMapper;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import static org.junit.Assert.assertEquals;

public class FindOneBookTest {

    ObjectMapper objectMapper = new ObjectMapper();

    Book book;

    @Given("^a running application\\.$")
    public void a_running_application() throws Throwable {

    }

    @When("^I access the endpoint \"([^\"]*)\" by GET$")
    public void i_access_the_endpoint_by(String endpoint) throws Throwable {
        CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
        HttpGet getRequest = new HttpGet("http://localhost:8080" + endpoint);
        getRequest.addHeader("content-type", "application/json");
        HttpResponse response = closeableHttpClient.execute(getRequest);
        book = objectMapper.readValue(response.getEntity().getContent(), Book.class);

    }

    @Then("^it should return that the book has \"([^\"]*)\" pages$")
    public void it_should_return_that_the_book_has_pages(Integer pages) throws Throwable {
        assertEquals(pages, book.getPages());
    }

    @Then("^should return that the book is of the genre of \"([^\"]*)\" and the author is \"([^\"]*)\"$")
    public void should_return_that_the_book_is_of_the_genre_of_Comic_Book_and_the_author_is(String genre, String author) throws Throwable {
        assertEquals(genre, book.getGenre());
        assertEquals(author, book.getAuthor());
    }

}
