package com.deividsantos.bdd.repository;

import com.deividsantos.bdd.dto.Book;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
public class BookRepository {

    public List<Book> getAllBooks() {
        return Arrays.asList(
                new Book("Harry Potter and the Chamber of Secrets", 256, "Fantasy", "J. K. Rowling"),
                new Book("A Brief History of Time", 385, "Science", "Stephen Hawkings"),
                new Book("Watchmen", 650, "Comic Book", "Alan Moore"));
    }

}
