package com.automation.steps;

import com.automation.base.BaseTestManager;
import com.automation.pages.WikipediaArticlePage;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ViewArticleOnWikipediaStepDefinitions {
    private BaseTestManager testManager;
    private WikipediaArticlePage pageObject;

    @Before
    public void setUp() throws Exception {
        testManager = new BaseTestManager();
        testManager.initializeBrowser();
        pageObject = new WikipediaArticlePage(testManager);
    }

    @Given("I am on the Wikipedia homepage")
    public void iAmOnTheWikipediaHomepage() {
        pageObject.navigateToWikipedia();
    }

    @When("I search for the article {string}")
    public void iSearchForTheArticle(String articleName) {
        pageObject.searchForArticle(articleName);
    }

    @When("I open the article")
    public void iOpenTheArticle() {
        pageObject.openArticle();
    }

    @Then("I should see the article {string}")
    public void iShouldSeeTheArticle(String expectedTitle) {
        pageObject.verifyArticlePageLoaded(expectedTitle);
    }

    @Then("I take a screenshot of the article page")
    public void iTakeAScreenshotOfTheArticlePage() {
        pageObject.takeScreenshot("wikipedia-article.png");
    }

    @After
    public void tearDown() {
        testManager.closeBrowser();
    }
}