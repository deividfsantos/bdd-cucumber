package com.deividsantos.bdd.repository;

import com.deividsantos.bdd.dto.Book;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BookRepository {

    private static String PATH = "src/main/resources/com.deividsantos.bdd/books.txt";

    public List<Book> getAllBooks() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(new File(PATH)));

        List<Book> books = new ArrayList<>();
        String st;
        while ((st = br.readLine()) != null) {
            String[] split = st.split("\\|");
            books.add(new Book(Integer.valueOf(split[0]), split[1].trim(), Integer.valueOf(split[2]), split[3].trim(), split[4].trim()));
        }

        return books;
    }
}