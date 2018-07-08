package com.deividsantos.bdd.repository;

import com.deividsantos.bdd.dto.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Book> findAll() {
        return jdbcTemplate.query("select * from book",
                new Object[]{}, new BeanPropertyRowMapper<>(Book.class));
    }

    public Book findByCode(Long code) {
        return jdbcTemplate.queryForObject("select * from book where code = ?",
                new Object[]{code}, new BeanPropertyRowMapper<>(Book.class));
    }

    public void save(Book book) {
        jdbcTemplate.update("insert into book (name, pages, genre, author) values (?,?,?,?)",
                book.getName(),
                book.getPages(),
                book.getGenre(),
                book.getAuthor());
    }
}