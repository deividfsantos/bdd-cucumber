package com.deividsantos.bdd.service;

import com.deividsantos.bdd.dto.Book;
import com.deividsantos.bdd.repository.BookRepository;
import com.deividsantos.bdd.repository.RentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.List;

@Repository
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private RentalRepository rentalRepository;

    @Autowired
    private ClientService clientService;

    public List<Book> get() throws IOException {
        return bookRepository.findAll();
    }

    public Book get(Integer code) throws Exception {
        return bookRepository.find(code);
    }

    public void insert(Book book) throws IOException {
        bookRepository.save(book);
    }

    public void rent(Integer clientCode, Integer bookCode) throws Exception {
        try {
            clientService.get(clientCode);
            if (get(bookCode).getStock() < 1) {
                throw new Exception("This book doesn't have enough stock.");
            }
        } catch (Exception e) {
            throw new Exception("No book or client found with these codes.");
        }

        rentalRepository.rentABook(clientCode, bookCode);
        bookRepository.updateStock(bookCode);
    }
}
