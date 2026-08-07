package com.automation.steps;

import com.automation.base.BaseTestManager;
import com.automation.pages.W3SchoolsSearchPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class W3SchoolsSearchSteps {
    private BaseTestManager testManager;
    private W3SchoolsSearchPage searchPage;

    public W3SchoolsSearchSteps(BaseTestManager testManager) {
        this.testManager = testManager;
        this.searchPage = new W3SchoolsSearchPage(testManager);
    }

    @Given("I navigate to W3Schools website")
    public void navigateToW3Schools() {
        searchPage.navigateToW3Schools();
    }

    @When("I click on the search input box at the top")
    public void clickSearchBox() {
        searchPage.clickSearchBox();
    }

    @When("I enter {string} in the search box")
    public void enterSearchQuery(String query) {
        searchPage.enterSearchQuery(query);
    }

    @Then("I should see search results for {string}")
    public void verifySearchResults(String expectedText) {
        searchPage.waitForSearchResults();
        searchPage.verifySearchResults(expectedText);
    }
}