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
        pageObject.searchWikipedia(query);
        pageObject.clickSearchButton();
    }

    @When("I click on the {string} article link")
    public void iClickOnTheArticleLink(String articleTitle) {
        pageObject.clickArticleLink(articleTitle);
    }

    @Then("I should see the {string} article page")
    public void iShouldSeeTheArticlePage(String expectedTitle) {
        pageObject.verifyArticlePageLoaded(expectedTitle);
    }

    @After
    public void tearDown() {
        testManager.closeBrowser();
    }
}