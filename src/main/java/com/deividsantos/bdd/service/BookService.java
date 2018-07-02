package com.deividsantos.bdd.service;

import com.deividsantos.bdd.dto.Book;
import com.deividsantos.bdd.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public List<Book> getBooks() {
        return bookRepository.getAllBooks();
    }

    public Book getBook(Integer code) throws Exception {
        return getBooks().stream()
                .filter(book -> book.getCode()==code)
                .findFirst()
                .orElseThrow(() -> new Exception());
    }
}
