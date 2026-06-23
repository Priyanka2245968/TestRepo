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
        testManager.getPage().navigate(url);
    }

    @When("I search for {string} in the Wikipedia search field")
    public void iSearchForInTheWikipediaSearchField(String topic) throws Exception {
        pageObject.searchForTopic(topic);
    }

    @When("I click the {string} link in the search results")
    public void iClickTheLinkInTheSearchResults(String resultLink) throws Exception {
        pageObject.clickSearchResult(resultLink);
    }

    @Then("the test should complete successfully")
    public void theTestShouldCompleteSuccessfully() throws Exception {
        pageObject.takeScreenshot("wikipedia-article-" + System.currentTimeMillis() + ".png");
    }

    @After
    public void tearDown() throws Exception {
        testManager.closeBrowser();
    }
}