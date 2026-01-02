package com.ibm.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"com.ibm.steps", "com.ibm.hooks"},
        tags = "@ECommerce",
        plugin = {"pretty", "html:target/cucumber-reports.html"}
)

public class RunUsingTestNg extends AbstractTestNGCucumberTests {
}
