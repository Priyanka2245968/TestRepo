package com.automation.steps;

import com.automation.pages.WikipediaSearchPage;
import com.automation.base.BaseTestManager;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.testng.Assert.assertTrue;

public class WikipediaSearchSteps {
    private WikipediaSearchPage wikipediaPage;

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

    @Then("I should see search results")
    public void verifySearchResultsLoaded() {
        wikipediaPage.verifySearchResultsLoaded();
    }

    @When("I click the first search result")
    public void clickFirstSearchResult() {
        wikipediaPage.clickFirstSearchResult();
    }

    @Then("I should see the {string} article page")
    public void verifyArticlePageLoaded(String articleTitle) {
        wikipediaPage.verifyArticlePageLoaded(articleTitle);
    }

    @Then("I should see no search results")
    public void verifyNoSearchResults() {
        assertTrue(wikipediaPage.page.locator("div.mw-search-nonefound").isVisible());
    }
}