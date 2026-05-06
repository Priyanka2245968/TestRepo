package com.automation.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
    features = "src/test/resources/features",
    glue = {"com.automation.steps"},
    plugin = {
        "pretty",
        "html:target/cucumber-reports/cucumber.html",
        "json:target/cucumber-reports/Cucumber.json"
    },
    monochrome = true
)
public class WikipediaSearchRunner extends AbstractTestNGCucumberTests {
}