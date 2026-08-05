package com.automation.steps;

import io.cucumber.java.en.*;
import io.cucumber.java.Before;
import io.cucumber.java.After;
import com.automation.base.BaseTestManager;
import com.automation.pages.WikipediaArticleViewPage;

public class WikipediaArticleViewStepDefinitions {
    private BaseTestManager testManager;
    private WikipediaArticleViewPage pageObject;

    @Before
    public void setUp() throws Exception {
        testManager = new BaseTestManager();
        testManager.initializeBrowser();
        pageObject = new WikipediaArticleViewPage(testManager);
    }

    @Given("I navigate to Wikipedia homepage")
    public void iNavigateToWikipediaHomepage() throws Exception {
        pageObject.navigateToWikipediaHomepage();
    }

    @When("I enter {string} in the search box")
    public void iEnterInTheSearchBox(String text) throws Exception {
        pageObject.enterSearchText(text);
    }

    @When("I click the Search button")
    public void iClickTheSearchButton() throws Exception {
        pageObject.clickSearchButton();
    }

    @When("I click the first search result link titled {string}")
    public void iClickTheFirstSearchResultLinkTitled(String linkText) throws Exception {
        pageObject.clickFirstSearchResult();
    }

    @Then("I take a screenshot of the article page")
    public void iTakeAScreenshotOfTheArticlePage() throws Exception {
        pageObject.takeScreenshot("bdd-wikipedia-article-view-" + System.currentTimeMillis() + ".png");
    }

    @After
    public void tearDown() throws Exception {
        testManager.closeBrowser();
    }
}