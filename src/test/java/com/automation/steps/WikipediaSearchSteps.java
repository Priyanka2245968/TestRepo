package com.automation.steps;

import com.automation.pages.WikipediaSearchPage;
import com.automation.base.BaseTestManager;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class WikipediaSearchSteps {
    private final WikipediaSearchPage wikipediaPage;

    public WikipediaSearchSteps(BaseTestManager testManager) {
        this.wikipediaPage = new WikipediaSearchPage(testManager);
    }

    @Given("I navigate to Wikipedia")
    public void navigateToWikipedia() {
        wikipediaPage.navigateToWikipedia();
    }

    @When("I search for {string}")
    public void searchForTerm(String term) {
        wikipediaPage.searchForTerm(term);
    }

    @Then("I should see the search results for {string}")
    public void verifySearchResults(String term) {
        wikipediaPage.verifySearchResults(term);
    }

    @When("I click the HTML link in search results")
    public void clickHtmlLink() {
        wikipediaPage.clickHtmlLink();
    }

    @Then("I should see the HTML article page")
    public void verifyHtmlArticle() {
        wikipediaPage.verifyHtmlArticle();
    }
}