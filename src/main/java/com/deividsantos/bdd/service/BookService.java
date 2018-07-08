package com.deividsantos.bdd.service;

import com.deividsantos.bdd.dto.Book;
import com.deividsantos.bdd.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.List;

@Repository
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public List<Book> getBooks() throws IOException {
        return bookRepository.findAll();
    }

    public Book getBook(Long code) throws Exception {
        return bookRepository.findByCode(code);
    }

    public void insertBook(Book book) throws IOException {
        bookRepository.save(book);
    }
}
