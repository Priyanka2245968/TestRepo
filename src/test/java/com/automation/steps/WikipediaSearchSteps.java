package com.automation.steps;

import com.automation.pages.WikipediaSearchPage;
import com.automation.base.BaseTestManager;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class WikipediaSearchSteps {
    private WikipediaSearchPage wikipediaPage;

    public WikipediaSearchSteps(BaseTestManager testManager) {
        this.wikipediaPage = new WikipediaSearchPage(testManager);
    }

    @Given("I navigate to Wikipedia")
    public void navigateToWikipedia() {
        wikipediaPage.navigateToWikipedia();
    }

    @When("I click the Search Wikipedia button")
    public void clickSearchButton() {
        wikipediaPage.clickSearchButton();
    }

    @When("I enter {string} in the Search Wikipedia field")
    public void enterSearchTerm(String term) {
        wikipediaPage.enterSearchTerm(term);
    }

    @Then("the search field should contain {string}")
    public void verifySearchFieldValue(String expectedValue) {
        String actualValue = wikipediaPage.getSearchFieldValue();
        Assert.assertEquals(actualValue, expectedValue, "Search field value mismatch");
    }

    @Then("the search results page should be displayed")
    public void verifySearchResultsPage() {
        // Add appropriate assertions to verify the search results page
        Assert.assertTrue(true, "Search results page verification is missing");
    }

    @Then("an error message {string} should be displayed")
    public void verifyErrorMessage(String expectedErrorMessage) {
        // Add code to retrieve and assert the error message
        Assert.fail("Error message verification is not implemented");
    }
}