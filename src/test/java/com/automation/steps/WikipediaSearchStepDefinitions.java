package com.automation.steps;

import com.automation.base.BaseTestManager;
import com.automation.pages.WikipediaSearchPage;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class WikipediaSearchStepDefinitions {
    private BaseTestManager testManager;
    private WikipediaSearchPage pageObject;

    @Before
    public void setUp() throws Exception {
        testManager = new BaseTestManager();
        testManager.initializeBrowser();
        pageObject = new WikipediaSearchPage(testManager);
    }

    @Given("I navigate to {string}")
    public void iNavigateTo(String url) throws Exception {
        testManager.getPage().navigate(url);
    }

    @When("I execute step {int}: {string}")
    public void executeStep(int stepNum, String description) throws Exception {
        switch (stepNum) {
            case 1 -> pageObject.navigateToWikipedia();
            case 2 -> pageObject.searchForTerm("Playwright");
            case 3 -> pageObject.pressEnter();
            case 4 -> pageObject.verifyArticleHeading("Playwright");
        }
    }

    @Then("the test should complete successfully")
    public void theTestShouldCompleteSuccessfully() throws Exception {
        pageObject.takeScreenshot("wikipedia-search-result-" + System.currentTimeMillis() + ".png");
    }

    @After
    public void tearDown() throws Exception {
        testManager.closeBrowser();
    }
}