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

    @When("I execute step {string}")
    public void executeStep(String stepDescription) throws Exception {
        switch (stepDescription) {
            case "Navigate to https://www.wikipedia.org" -> pageObject.navigateToWikipedia();
            case "Type 'Playwright' into the search input" -> pageObject.searchForTerm("Playwright");
            case "Press Enter to search" -> pageObject.pressEnterToSearch();
            case "Verify the article page shows the heading 'Playwright'" -> pageObject.verifyArticleHeading("Playwright");
        }
    }

    @Then("the test should complete successfully")
    public void theTestShouldCompleteSuccessfully() throws Exception {
        pageObject.takeScreenshot("wikipedia-search-results.png");
    }

    @After
    public void tearDown() throws Exception {
        testManager.closeBrowser();
    }
}