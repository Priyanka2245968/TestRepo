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
    private WikipediaArticlePage wikipediaPage;

    @Before
    public void setUp() throws Exception {
        testManager = new BaseTestManager();
        testManager.initializeBrowser();
        wikipediaPage = new WikipediaArticlePage(testManager);
    }

    @Given("I navigate to Wikipedia")
    public void iNavigateToWikipedia() throws Exception {
        wikipediaPage.navigateToWikipedia();
    }

    @When("I search for {string}")
    public void iSearchFor(String topic) throws Exception {
        wikipediaPage.searchForTopic(topic);
    }

    @When("I click on the {string} article link")
    public void iClickOnTheArticleLink(String articleTitle) throws Exception {
        wikipediaPage.clickArticleLink(articleTitle);
    }

    @Then("the {string} article page should load successfully")
    public void theArticlePageShouldLoadSuccessfully(String articleTitle) throws Exception {
        wikipediaPage.takeScreenshot("article_" + articleTitle + ".png");
    }

    @After
    public void tearDown() throws Exception {
        testManager.closeBrowser();
    }
}