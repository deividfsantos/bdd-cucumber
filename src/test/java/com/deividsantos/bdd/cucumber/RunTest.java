package com.deividsantos.bdd.cucumber;

import com.deividsantos.bdd.BookServiceApplication;
import com.deividsantos.bdd.TestConfig;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.test.context.ContextConfiguration;

@RunWith(Cucumber.class)
@ContextConfiguration(loader = SpringBootContextLoader.class, classes = BookServiceApplication.class)
public class RunTest extends TestConfig {

}