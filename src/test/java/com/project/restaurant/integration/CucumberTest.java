package com.project.restaurant.integration;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(glue = {"com.project.restaurant.integration.glue"}, features = "classpath:features")
public class CucumberTest {
}
