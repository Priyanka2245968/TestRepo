package com.automation.steps;

import com.automation.base.BaseTestManager;
import com.automation.pages.ViewArticleOnWikipediaPage;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ViewArticleOnWikipediaStepDefinitions {

    private BaseTestManager testManager;
    private ViewArticleOnWikipediaPage pageObject;

    @Before
    public void setUp() {
        testManager = new BaseTestManager();
        testManager.initializeBrowser();
        pageObject = new ViewArticleOnWikipediaPage(testManager);
    }

    @Given("I navigate to {string}")
    public void iNavigateTo(String url) {
        testManager.getPage().navigate(url);
    }

    @When("I execute step {int}: {string}")
    public void executeStep(int stepNumber, String description) {
        switch (stepNumber) {
            case 2:
                pageObject.fillSearchField(description);
                break;
            case 3:
                pageObject.clickSearchButton();
                break;
            case 4:
                pageObject.clickTopSearchResult();
                break;
            default:
                throw new RuntimeException("Invalid step number: " + stepNumber);
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