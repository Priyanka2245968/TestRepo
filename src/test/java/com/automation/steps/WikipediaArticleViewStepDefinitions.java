package com.automation.steps;

import io.cucumber.java.en.*;
import io.cucumber.java.Before;
import io.cucumber.java.After;
import com.automation.base.BaseTestManager;
import com.automation.pages.WikipediaArticleViewPage;

public class WikipediaArticleViewStepDefinitions {
    private BaseTestManager testManager;
    private WikipediaArticleViewPage pageObject;
    
    @Before
    public void setUp() throws Exception {
        testManager = new BaseTestManager();
        testManager.initializeBrowser();
        pageObject = new WikipediaArticleViewPage(testManager);
    }
    
    @Given("I navigate to {string}")
    public void iNavigateTo(String url) throws Exception {
        System.out.println("🌐 Navigating to: " + url);
        testManager.getPage().navigate(url);
    }
    
    @When("I enter {string} in the search box")
    public void iEnterInTheSearchBox(String query) throws Exception {
        pageObject.enterSearchQuery(query);
    }
    
    @When("I click the Search button")
    public void iClickTheSearchButton() throws Exception {
        pageObject.clickSearchButton();
    }
    
    @When("I click the {string} link in search results")
    public void iClickTheLinkInSearchResults(String linkText) throws Exception {
        pageObject.clickSearchResultLink(linkText);
    }
    
    @Then("the test should complete successfully")
    public void theTestShouldCompleteSuccessfully() throws Exception {
        pageObject.takeScreenshot("bdd-wikipedia-article-view-" + System.currentTimeMillis() + ".png");
        System.out.println("✅ Test completed successfully!");
    }
    
    @After
    public void tearDown() throws Exception {
        testManager.closeBrowser();
    }
}