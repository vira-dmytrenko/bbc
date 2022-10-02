package com.bbc.test.runner;

import io.cucumber.junit.Cucumber;
import org.junit.runner.RunWith;
import io.cucumber.junit.CucumberOptions;


@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/main/resources/part1.feature",
    glue = "com/bbc/stepdefinitions"
)
public class PartOneTests {

}