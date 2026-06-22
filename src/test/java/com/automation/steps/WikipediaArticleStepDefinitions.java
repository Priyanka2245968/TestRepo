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

    @Given("I navigate to {string}")
    public void iNavigateTo(String url) throws Exception {
        testManager.getPage().navigate(url);
    }

    @When("I search for {string} topic")
    public void iSearchForTopic(String topic) throws Exception {
        wikipediaPage.searchForTopic(topic);
    }

    @When("I open the {string} article")
    public void iOpenTheArticle(String articleTitle) throws Exception {
        wikipediaPage.openArticle(articleTitle);
    }

    @Then("The {string} article page is displayed")
    public void theArticlePageIsDisplayed(String articleTitle) throws Exception {
        wikipediaPage.verifyArticleDisplayed(articleTitle);
        wikipediaPage.takeScreenshot("article-" + articleTitle + ".png");
    }

    @After
    public void tearDown() throws Exception {
        testManager.closeBrowser();
    }
}