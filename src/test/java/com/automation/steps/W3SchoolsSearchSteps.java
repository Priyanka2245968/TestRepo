package com.automation.steps;

import com.automation.base.BaseTestManager;
import com.automation.pages.W3SchoolsSearchPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class W3SchoolsSearchSteps {
    private BaseTestManager testManager;
    private W3SchoolsSearchPage w3SchoolsPage;

    public W3SchoolsSearchSteps(BaseTestManager testManager) {
        this.testManager = testManager;
        this.w3SchoolsPage = new W3SchoolsSearchPage(testManager);
    }

    @Given("I navigate to W3Schools website")
    public void navigateToW3Schools() {
        w3SchoolsPage.navigateToW3Schools();
    }

    @When("I click on the search input box at the top")
    public void clickSearchBox() {
        w3SchoolsPage.clickSearchBox();
    }

    @When("I enter {string} in the search box")
    public void enterSearchQuery(String query) {
        w3SchoolsPage.enterSearchQuery(query);
    }

    @When("I submit the search")
    public void submitSearch() {
        w3SchoolsPage.submitSearch();
    }

    @Then("I should see search results for {string}")
    public void verifySearchResults(String query) {
        w3SchoolsPage.verifySearchResults();
        System.out.println("✅ Search results displayed for: " + query);
    }
}