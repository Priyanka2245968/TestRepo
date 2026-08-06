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
    
    @Given("I navigate to Wikipedia homepage")
    public void iNavigateToWikipediaHomepage() throws Exception {
        pageObject.navigateToWikipedia();
    }
    
    @When("I enter {string} in the search box")
    public void iEnterInTheSearchBox(String query) throws Exception {
        pageObject.enterSearchQuery(query);
    }
    
    @When("I click the Search button")
    public void iClickTheSearchButton() throws Exception {
        pageObject.clickSearchButton();
    }
    
    @When("I click the first search result link")
    public void iClickTheFirstSearchResultLink() throws Exception {
        pageObject.clickFirstSearchResult();
    }
    
    @Then("the test should complete successfully")
    public void theTestShouldCompleteSuccessfully() throws Exception {
        pageObject.takeScreenshot("bdd-wikipedia-search-test-" + System.currentTimeMillis() + ".png");
        System.out.println("✅ Test completed successfully!");
    }
    
    @After
    public void tearDown() throws Exception {
        testManager.closeBrowser();
    }
}