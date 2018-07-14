package com.deividsantos.bdd;

import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, classes = BookServiceApplication.class)
public class TestConfig {
}
