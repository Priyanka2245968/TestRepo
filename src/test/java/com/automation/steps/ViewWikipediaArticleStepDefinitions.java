package com.automation.steps;

import com.automation.base.BaseTestManager;
import com.automation.pages.WikipediaArticlePage;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.Map;

public class ViewWikipediaArticleStepDefinitions {
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
        pageObject.navigateToWikipedia();
    }

    @When("I perform the following steps:")
    public void performSteps(Map<Integer, String> steps) throws Exception {
        for (Map.Entry<Integer, String> step : steps.entrySet()) {
            switch (step.getKey()) {
                case 1 -> pageObject.searchForArticle("Python programming language");
                case 2 -> pageObject.openArticle("Python (programming language)");
                case 3 -> pageObject.verifyArticleLoaded("Python (programming language) - Wikipedia");
            }
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
