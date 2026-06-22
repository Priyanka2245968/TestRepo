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

    @Given("I navigate to {string}")
    public void iNavigateTo(String url) throws Exception {
        pageObject.navigateToWikipedia(url);
    }

    @When("I search for {string}")
    public void iSearchFor(String query) throws Exception {
        pageObject.searchWikipedia(query);
    }

    @When("I click the search result {string}")
    public void iClickTheSearchResult(String title) throws Exception {
        pageObject.clickSearchResult(title);
    }

    @Then("I should see the {string} article")
    public void iShouldSeeTheArticle(String title) throws Exception {
        pageObject.verifyArticleTitle(title);
        pageObject.takeScreenshot("article_" + title.replaceAll("\\s+", "_").toLowerCase());
    }

    @After
    public void tearDown() throws Exception {
        testManager.closeBrowser();
    }
}