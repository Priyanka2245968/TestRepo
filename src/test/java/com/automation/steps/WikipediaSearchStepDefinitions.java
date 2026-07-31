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
    public void iNavigateTo(String url) {
        System.out.println("🌐 Navigating to: " + url);
        pageObject.navigateToWikipediaHomepage();
    }
    
    @When("I enter {string} in the search box")
    public void iEnterInTheSearchBox(String term) throws Exception {
        pageObject.enterSearchTerm(term);
    }
    
    @When("I click the Search Wikipedia button")
    public void iClickTheSearchWikipediaButton() throws Exception {
        pageObject.clickSearchButton();
    }
    
    @When("I click the top search result link")
    public void iClickTheTopSearchResultLink() throws Exception {
        pageObject.clickTopSearchResult();
    }
    
    @Then("the search box should be visible")
    public void theSearchBoxShouldBeVisible() throws Exception {
        pageObject.verifySearchBoxVisible();
    }
    
    @Then("the search box should have value {string}")
    public void theSearchBoxShouldHaveValue(String value) throws Exception {
        pageObject.verifySearchBoxValue(value);
    }
    
    @Then("the search results should show {string}")
    public void theSearchResultsShouldShow(String text) throws Exception {
        pageObject.verifySearchResultsText(text);
    }
    
    @Then("multiple search results should be listed")
    public void multipleSearchResultsShouldBeListed() throws Exception {
        pageObject.verifyMultipleSearchResults();
    }
    
    @Then("the Wikipedia article page should load with content")
    public void theWikipediaArticlePageShouldLoadWithContent() throws Exception {
        pageObject.verifyArticleContentVisible();
    }
    
    @Then("the test should complete successfully")
    public void theTestShouldCompleteSuccessfully() throws Exception {
        pageObject.takeScreenshot("bdd-wikipedia-search-" + System.currentTimeMillis() + ".png");
        System.out.println("✅ Test completed successfully!");
    }
    
    @After
    public void tearDown() throws Exception {
        testManager.closeBrowser();
    }
}