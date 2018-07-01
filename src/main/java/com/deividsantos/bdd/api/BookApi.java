package com.deividsantos.bdd.api;

import com.deividsantos.bdd.output.BookOutput;
import com.deividsantos.bdd.service.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public List<BookOutput> getAllBooks() {
        return bookService.getBooks().stream()
                .map(book -> objectMapper.convertValue(book, BookOutput.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/{name}")
    public BookOutput getBook(@PathVariable("name") String name) throws Exception {
        try {
            return objectMapper.convertValue(bookService.getBook(name), BookOutput.class);
        } catch (Exception e) {
            throw new Exception("No book found with the name entered.");
        }
    }

}
