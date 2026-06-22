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

    @When("I execute step {string}: {string}")
    public void executeStep(String stepNumber, String description) throws Exception {
        switch (stepNumber) {
            case "1":
                pageObject.searchForArticle(description);
                break;
            case "2":
                pageObject.openArticle(description);
                break;
            case "3":
                boolean isArticleDisplayed = pageObject.isArticleDisplayed(description);
                System.out.println("Article '" + description + "' is displayed: " + isArticleDisplayed);
                break;
            default:
                throw new Exception("Invalid step number: " + stepNumber);
        }
    }

    @Then("the test should complete successfully")
    public void theTestShouldCompleteSuccessfully() throws Exception {
        pageObject.takeScreenshot("wikipedia-article-test.png");
    }

    @After
    public void tearDown() throws Exception {
        testManager.closeBrowser();
    }
}