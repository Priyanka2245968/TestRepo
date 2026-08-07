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
        pageObject.navigateToWikipediaHomepage();
    }
    
    @When("I enter {string} in the search field")
    public void iEnterInTheSearchField(String term) throws Exception {
        System.out.println("📍 Entering '" + term + "' in the search field");
        pageObject.enterSearchTerm(term);
    }
    
    @When("I click the Search button")
    public void iClickTheSearchButton() throws Exception {
        System.out.println("📍 Clicking the 'Search' button");
        pageObject.clickSearchButton();
    }
    
    @When("I click the {string} link in the search results")
    public void iClickTheLinkInTheSearchResults(String result) throws Exception {
        System.out.println("📍 Clicking the '" + result + "' link in the search results");
        pageObject.clickSearchResult(result);
    }
    
    @Then("the {string} article page content is visible")
    public void theArticlePageContentIsVisible(String term) throws Exception {
        System.out.println("📍 Verifying the '" + term + "' article page content is visible");
        pageObject.verifyArticleContentVisible(term);
    }
    
    @Then("the URL contains {string} after navigating to the article")
    public void theURLContainsAfterNavigatingToTheArticle(String path) throws Exception {
        System.out.println("📍 Verifying the URL contains '" + path + "' after navigating to the article");
        pageObject.verifyUrlContains(path);
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