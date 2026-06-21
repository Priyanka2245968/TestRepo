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

    @When("I execute step {string}")
    public void executeStep(String stepDescription) throws Exception {
        switch (stepDescription) {
            case "Enter 'HTML Tutorial' in the search field":
                testManager.getPage().locator("#searchInput").first().fill("HTML Tutorial");
                break;
            case "Click the 'Search' button":
                testManager.getPage().locator("#searchButton").first().click();
                break;
            case "Click the first search result link":
                testManager.getPage().locator(".mw-search-results li a").first().click();
                break;
            default:
                throw new IllegalArgumentException("Unknown step: " + stepDescription);
        }
    }

    @Then("the test should complete successfully")
    public void theTestShouldCompleteSuccessfully() throws Exception {
        pageObject.verifyArticlePageLoaded("HTML Tutorial - Wikipedia");
        pageObject.takeScreenshot("wikipedia_article");
    }

    @After
    public void tearDown() throws Exception {
        testManager.closeBrowser();
    }
}
