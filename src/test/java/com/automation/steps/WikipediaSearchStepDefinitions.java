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
        pageObject.navigateToWikipediaHomePage();
    }
    
    @When("I enter {string} in the search box")
    public void iEnterInTheSearchBox(String term) throws Exception {
        System.out.println("📍 Entering '" + term + "' in the search box");
        pageObject.enterSearchTerm(term);
    }
    
    @When("I click the Search button")
    public void iClickTheSearchButton() throws Exception {
        System.out.println("📍 Clicking the Search button");
        pageObject.clickSearchButton();
    }
    
    @When("I click the {string} link in search results")
    public void iClickTheLinkInSearchResults(String result) throws Exception {
        System.out.println("📍 Clicking the '" + result + "' link in search results");
        pageObject.clickSearchResult(result);
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