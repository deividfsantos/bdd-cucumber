package com.deividsantos.bdd.repository;

import com.deividsantos.bdd.dto.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ClientRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Client> findAll() {
        return jdbcTemplate.query("select * from client",
                new Object[]{}, new BeanPropertyRowMapper<>(Client.class));
    }

    public Client find(Integer code) {
        return jdbcTemplate.queryForObject("select * from client where code = ?",
                new Object[]{code}, new BeanPropertyRowMapper<>(Client.class));
    }

    public void save(Client client) {
        jdbcTemplate.update("insert into client (name, phone, nationality) values (?,?,?)",
                client.getName(),
                client.getPhone(),
                client.getNationality()
        );
    }

}
