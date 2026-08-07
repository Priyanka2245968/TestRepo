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
    
    @When("I enter {string} in the search box")
    public void iEnterInTheSearchBox(String text) throws Exception {
        System.out.println("📍 Enter '" + text + "' in the search box");
        pageObject.enterSearchText(text);
    }
    
    @When("I click the Search button")
    public void iClickTheSearchButton() throws Exception {
        System.out.println("📍 Click the Search button");
        pageObject.clickSearchButton();
    }
    
    @When("I wait for search results page to load")
    public void iWaitForSearchResultsPageToLoad() throws Exception {
        System.out.println("📍 Wait for search results page to load");
        pageObject.waitForSearchResults();
    }
    
    @When("I click on the first {string} search result")
    public void iClickOnTheFirstSearchResult(String resultText) throws Exception {
        System.out.println("📍 Click on the first '" + resultText + "' search result");
        pageObject.clickSearchResult(resultText);
    }
    
    @When("I wait for {string} article page to load")
    public void iWaitForArticlePageToLoad(String articleTitle) throws Exception {
        System.out.println("📍 Wait for '" + articleTitle + "' article page to load");
        pageObject.waitForArticlePage();
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