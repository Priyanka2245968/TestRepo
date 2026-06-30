package com.automation.steps;

import com.automation.pages.WikipediaSearchPage;
import com.automation.base.BaseTestManager;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

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
        assertThat(wikipediaPage.page).hasTitle("HTML - Wikipedia");
    }

    @Then("an error message {string} should be displayed")
    public void verifyErrorMessage(String expectedMessage) {
        assertThat(wikipediaPage.page).containsText(expectedMessage);
    }
}