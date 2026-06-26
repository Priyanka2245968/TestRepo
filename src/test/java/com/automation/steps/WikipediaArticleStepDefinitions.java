package com.automation.steps;

import com.automation.base.BaseTestManager;
import com.automation.pages.WikipediaArticlePage;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class WikipediaArticleStepDefinitions {
    private BaseTestManager testManager;
    private WikipediaArticlePage pageObject;

    @Before
    public void setUp() throws Exception {
        testManager = new BaseTestManager();
        testManager.initializeBrowser();
        pageObject = new WikipediaArticlePage(testManager);
    }

    @Given("I navigate to {string}")
    public void iNavigateTo(String url) throws Exception {
        testManager.getPage().navigate(url);
    }

    @When("I search for {string} on Wikipedia")
    public void iSearchForOnWikipedia(String query) {
        pageObject.navigateToWikipedia();
        pageObject.searchForArticle(query);
        pageObject.clickSearchButton();
    }

    @Then("I should see search results for {string}")
    public void iShouldSeeSearchResultsFor(String query) {
        pageObject.verifySearchResults(query);
    }

    @When("I click the first search result")
    public void iClickTheFirstSearchResult() {
        pageObject.clickFirstResult();
    }

    @Then("I should see the article {string} loaded")
    public void iShouldSeeTheArticleLoaded(String expectedTitle) {
        pageObject.verifyArticleLoaded(expectedTitle);
    }

    @After
    public void tearDown() {
        testManager.closeBrowser();
    }
}