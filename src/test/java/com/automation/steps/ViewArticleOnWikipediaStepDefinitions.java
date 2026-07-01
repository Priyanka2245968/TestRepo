package com.automation.steps;

import com.automation.base.BaseTestManager;
import com.automation.pages.ViewArticleOnWikipediaPage;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class ViewArticleOnWikipediaStepDefinitions {
    private BaseTestManager testManager;
    private ViewArticleOnWikipediaPage pageObject;

    @Before
    public void setUp() throws Exception {
        testManager = new BaseTestManager();
        testManager.initializeBrowser();
        pageObject = new ViewArticleOnWikipediaPage(testManager);
    }

    @Given("I navigate to {string}")
    public void iNavigateTo(String url) throws Exception {
        testManager.getPage().navigate(url);
    }

    @When("I execute step {}: {string}")
    public void executeStep(int stepNumber, String description) throws Exception {
        switch (stepNumber) {
            case 1 -> pageObject.searchForTopic("Photosynthesis");
            case 2 -> pageObject.clickSearchButton();
            case 3 -> {
                pageObject.clickPhotosynthesisLink();
                assertThat(testManager.getPage()).hasTitle("Photosynthesis - Wikipedia");
            }
            // Add more cases for additional steps
            default -> throw new IllegalArgumentException("Invalid step number: " + stepNumber);
        }
    }

    @Then("the test should complete successfully")
    public void theTestShouldCompleteSuccessfully() {
        // No additional assertions needed
    }

    @After
    public void tearDown() {
        testManager.closeBrowser();
    }
}