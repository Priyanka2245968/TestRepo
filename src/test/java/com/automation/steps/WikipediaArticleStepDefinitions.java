package com.automation.steps;

import com.automation.base.BaseTestManager;
import com.automation.pages.WikipediaArticlePage;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class WikipediaArticleStepDefinitions {
    private BaseTestManager testManager;
    private WikipediaArticlePage pageObject;

    @Before
    public void setUp() throws Exception {
        testManager = new BaseTestManager();
        testManager.initializeBrowser();
        pageObject = new WikipediaArticlePage(testManager);
    }

    @Given("I navigate to Wikipedia")
    public void iNavigateToWikipedia() {
        pageObject.navigateToWikipedia();
    }

    @When("I search for {string}")
    public void iSearchFor(String query) {
        pageObject.searchForArticle(query);
    }

    @When("I open the {string} article")
    public void iOpenTheArticle(String articleTitle) {
        pageObject.openArticle(articleTitle);
    }

    @Then("the {string} article page should be loaded")
    public void theArticlePageShouldBeLoaded(String expectedTitle) {
        pageObject.verifyArticlePageLoaded(expectedTitle);
    }

    @After
    public void tearDown() {
        testManager.closeBrowser();
    }
}