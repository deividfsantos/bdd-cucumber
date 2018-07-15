package com.deividsantos.bdd.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class RentalRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void rentABook(Integer clientCode, Integer bookCode) {
        jdbcTemplate.update("insert into rental (client_code, book_code) values (?,?)",
                clientCode, bookCode);
    }

}
