package com.deividsantos.bdd.api;

import com.deividsantos.bdd.output.BookOutput;
import com.deividsantos.bdd.service.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/books")
public class BookApi {

    @Autowired
    private BookService bookService;

    @Autowired
    private ObjectMapper objectMapper;

    @GetMapping
    public List<BookOutput> getAllBooks() throws IOException {
        return bookService.getBooks().stream()
                .map(book -> objectMapper.convertValue(book, BookOutput.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/{code}")
    public BookOutput getBook(@PathVariable("code") Integer code) throws Exception {
        try {
            return objectMapper.convertValue(bookService.getBook(code), BookOutput.class);
        } catch (Exception e) {
            throw new Exception("No book found with the code entered.");
        }
    }

}
