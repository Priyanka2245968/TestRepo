package com.automation.steps;

import io.cucumber.java.en.*;
import io.cucumber.java.Before;
import io.cucumber.java.After;
import com.automation.base.BaseTestManager;
import com.automation.pages.WikipediaSearchPage;

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
        System.out.println("🌐 Navigating to: " + url);
        testManager.getPage().navigate(url);
    }

    @When("I enter {string} in the search bar")
    public void iEnterInTheSearchBar(String query) throws Exception {
        pageObject.enterSearchQuery(query);
    }

    @When("I press Enter to submit the search")
    public void iPressEnterToSubmitTheSearch() throws Exception {
        pageObject.pressEnterToSearch();
    }

    @When("I click on the first search result titled {string}")
    public void iClickOnTheFirstSearchResultTitled(String linkText) throws Exception {
        pageObject.clickSearchResult(linkText);
    }

    @Then("the test should complete successfully")
    public void theTestShouldCompleteSuccessfully() throws Exception {
        pageObject.takeScreenshot("bdd-screenshot-" + System.currentTimeMillis() + ".png");
        System.out.println("✅ Test completed successfully!");
    }

    @After
    public void tearDown() throws Exception {
        testManager.closeBrowser();
    }
}