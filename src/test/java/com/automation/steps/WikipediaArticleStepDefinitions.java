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
    public void setUp() {
        testManager = new BaseTestManager();
        testManager.initializeBrowser();
        pageObject = new WikipediaArticlePage(testManager);
    }

    @Given("I navigate to {string}")
    public void iNavigateTo(String url) {
        testManager.getPage().navigate(url);
    }

    @When("I execute step {int}: {string}")
    public void executeStep(int stepNumber, String description) {
        switch (stepNumber) {
            case 1 -> pageObject.navigateToWikipedia();
            case 2 -> pageObject.searchForTerm("HTML Tutorial");
            case 3 -> pageObject.clickSearchButton();
            // Add more cases for additional steps
        }
    }

    @Then("the test should complete successfully")
    public void theTestShouldCompleteSuccessfully() {
        pageObject.takeScreenshot("wikipedia-test-result.png");
    }

    @After
    public void tearDown() {
        testManager.closeBrowser();
    }
}