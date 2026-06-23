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
            case "In the 'Search Wikipedia' field, enter 'Python programming language'":
                pageObject.searchForArticle("Python programming language");
                break;
            case "Click the 'Search' icon button next to the search field":
                pageObject.searchForArticle("");
                break;
            case "From the search results page, click the 'Python (programming language)' link":
                pageObject.openArticle("Python (programming language)");
                break;
            default:
                throw new Exception("Step not implemented: " + stepDescription);
        }
    }

    @Then("the test should complete successfully")
    public void theTestShouldCompleteSuccessfully() throws Exception {
        pageObject.verifyArticlePageLoaded();
        pageObject.takeScreenshot("wikipedia-article-" + System.currentTimeMillis() + ".png");
    }

    @After
    public void tearDown() throws Exception {
        testManager.closeBrowser();
    }
}