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

    @When("I search Wikipedia for {string}")
    public void iSearchWikipediaFor(String searchTerm) throws Exception {
        pageObject.searchWikipedia(searchTerm);
    }

    @When("I click the search result {string}")
    public void iClickTheSearchResult(String resultLink) throws Exception {
        pageObject.clickSearchResult(resultLink);
    }

    @Then("I should see the error message {string}")
    public void iShouldSeeTheErrorMessage(String expectedMessage) throws Exception {
        pageObject.verifyErrorMessage(expectedMessage);
    }

    @Then("the test should complete successfully")
    public void theTestShouldCompleteSuccessfully() throws Exception {
        pageObject.takeScreenshot("wikipedia_test_completed.png");
    }

    @After
    public void tearDown() throws Exception {
        testManager.closeBrowser();
    }
}