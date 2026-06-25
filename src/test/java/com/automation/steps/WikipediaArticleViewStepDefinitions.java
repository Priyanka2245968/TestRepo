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
    
    @Then("Wikipedia homepage is displayed with search box visible")
    public void wikipediaHomepageIsDisplayedWithSearchBoxVisible() throws Exception {
        pageObject.verifySearchBoxVisible();
    }
    
    @When("I enter {string} in the search box")
    public void iEnterInTheSearchBox(String text) throws Exception {
        pageObject.enterSearchText(text);
    }
    
    @Then("Search box has {string} entered")
    public void searchBoxHasEntered(String value) throws Exception {
        pageObject.verifySearchBoxValue(value);
    }
    
    @When("I click the Search button")
    public void iClickTheSearchButton() throws Exception {
        pageObject.clickSearchButton();
    }
    
    @Then("List of search results is displayed")
    public void listOfSearchResultsIsDisplayed() throws Exception {
        pageObject.verifySearchResultsVisible();
    }
    
    @Then("Search results page shows {string} heading")
    public void searchResultsPageShowsHeading(String heading) throws Exception {
        pageObject.verifySearchResultsHeading(heading);
    }
    
    @When("I click the {string} link in search results")
    public void iClickTheLinkInSearchResults(String linkText) throws Exception {
        pageObject.clickArticleLink(linkText);
    }
    
    @Then("Photosynthesis article title is visible")
    public void photosynthesisArticleTitleIsVisible() throws Exception {
        pageObject.verifyArticleTitleVisible();
    }
    
    @Then("Photosynthesis article content is displayed")
    public void photosynthesisArticleContentIsDisplayed() throws Exception {
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