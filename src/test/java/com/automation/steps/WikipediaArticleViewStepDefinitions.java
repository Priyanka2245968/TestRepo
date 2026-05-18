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
    
    @Then("the search box should be visible")
    public void theSearchBoxShouldBeVisible() throws Exception {
        pageObject.verifySearchBoxVisible();
    }
    
    @Then("the search box should have {string} entered")
    public void theSearchBoxShouldHaveEntered(String expectedValue) throws Exception {
        pageObject.verifySearchBoxValue(expectedValue);
    }
    
    @Then("the list of search results should be displayed")
    public void theListOfSearchResultsShouldBeDisplayed() throws Exception {
        pageObject.verifySearchResultsVisible();
    }
    
    @Then("the search results page should show {string} heading")
    public void theSearchResultsPageShouldShowHeading(String expectedHeading) throws Exception {
        pageObject.verifySearchResultsHeading(expectedHeading);
    }
    
    @Then("the article page title should be visible")
    public void theArticlePageTitleShouldBeVisible() throws Exception {
        pageObject.verifyArticlePageTitleVisible();
    }
    
    @Then("the article content should be displayed")
    public void theArticleContentShouldBeDisplayed() throws Exception {
        pageObject.verifyArticleContentVisible();
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