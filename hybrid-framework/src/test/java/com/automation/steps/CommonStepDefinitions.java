package com.automation.steps;

import io.cucumber.java.en.Then;

/**
 * Common Step Definitions shared across all BDD tests
 */
public class CommonStepDefinitions {
    
    @Then("the test should complete successfully")
    public void the_test_should_complete_successfully() {
        System.out.println("✅ BDD Test completed successfully!");
    }
}
