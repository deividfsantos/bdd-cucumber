package com.deividsantos.bdd.api;

import com.deividsantos.bdd.dto.Book;
import com.deividsantos.bdd.input.BookInput;
import com.deividsantos.bdd.output.BookOutput;
import com.deividsantos.bdd.service.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
        return bookService.get().stream()
                .map(book -> objectMapper.convertValue(book, BookOutput.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/{code}")
    public BookOutput getBook(@PathVariable("code") Integer code) throws Exception {
        try {
            return objectMapper.convertValue(bookService.get(code), BookOutput.class);
        } catch (Exception e) {
            throw new Exception("No book found with the code entered.");
        }
    }

    @PostMapping
    public void insertBook(@RequestBody BookInput book) throws IOException {
        bookService.insert(objectMapper.convertValue(book, Book.class));
    }

    @PostMapping("/{bookCode}/rental/{clientCode}")
    public void rentABook(@PathVariable("clientCode") Integer clientCode, @PathVariable("bookCode") Integer bookCode) throws Exception {
        bookService.rent(clientCode, bookCode);
    }
}
