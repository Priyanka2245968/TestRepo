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

    @When("I execute step {}: {string}")
    public void executeStep(int stepNumber, String description) throws Exception {
        switch (stepNumber) {
            case 1 -> pageObject.navigateToWikipedia();
            case 2 -> pageObject.searchForArticle("Python programming language");
            case 3 -> pageObject.openArticle("Python (programming language)");
            case 4 -> pageObject.verifyArticleDisplayed("Python (programming language) - Wikipedia");
            case 5 -> pageObject.searchForArticle("");
            case 6 -> {
                String longSearchTerm = "a".repeat(500);
                pageObject.searchForArticle(longSearchTerm);
            }
        }
    }

    @Then("the test should complete successfully")
    public void theTestShouldCompleteSuccessfully() throws Exception {
        pageObject.takeScreenshot("test-completed.png");
    }

    @After
    public void tearDown() throws Exception {
        testManager.closeBrowser();
    }
}
