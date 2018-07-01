package com.deividsantos.bdd.repository;

import com.deividsantos.bdd.dto.Book;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
public class BookRepository {

    List<Book> books = Arrays.asList(
            new Book(1, "Harry Potter", 256, "Fantasy", "J. K. Rowling"),
            new Book(2, "A Brief History of Time", 385, "Science", "Stephen Hawkings"),
            new Book(3, "Watchmen", 650, "Comic Book", "Alan Moore"));

    public List<Book> getAllBooks() {
        return books;
    }

    public void insetBook(Book book) {
        books.add(book);
    }
}
