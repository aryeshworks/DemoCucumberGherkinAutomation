package com.ibm.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = "com.ibm.steps",
        plugin = {"pretty", "html:target/cucumber-reports.html"},
        monochrome = true
)

public class RunUsingTestNg extends AbstractTestNGCucumberTests {}
