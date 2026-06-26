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

    @When("I execute step {int}: {string}")
    public void executeStep(int stepNum, String description) throws Exception {
        switch (stepNum) {
            case 1 -> pageObject.navigateToWikipedia();
            case 2 -> pageObject.searchForArticle("Python programming language");
            case 3 -> pageObject.clickSearchButton();
            case 4 -> pageObject.verifySearchResults("Python programming language");
            case 5 -> pageObject.clickFirstResult();
            case 6 -> pageObject.verifyArticleLoaded("Python (programming language)");
            default -> throw new Exception("Invalid step number: " + stepNum);
        }
    }

    @Then("the test should complete successfully")
    public void theTestShouldCompleteSuccessfully() throws Exception {
        pageObject.takeScreenshot("wikipedia_article.png");
    }

    @After
    public void tearDown() throws Exception {
        testManager.closeBrowser();
    }
}