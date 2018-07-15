package com.deividsantos.bdd.cucumber;

import com.deividsantos.bdd.LibraryServiceApplication;
import com.deividsantos.bdd.TestConfig;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.test.context.ContextConfiguration;

@RunWith(Cucumber.class)
@ContextConfiguration(loader = SpringBootContextLoader.class, classes = LibraryServiceApplication.class)
public class RunTest extends TestConfig {

}