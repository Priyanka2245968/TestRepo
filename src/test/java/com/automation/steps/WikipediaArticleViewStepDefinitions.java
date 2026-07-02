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
    public void iEnterInTheSearchBox(String text) throws Exception {
        pageObject.enterSearchText(text);
    }
    
    @When("I click the Search button")
    public void iClickTheSearchButton() throws Exception {
        pageObject.clickSearchButton();
    }
    
    @When("I click the first {string} link in search results")
    public void iClickTheFirstLinkInSearchResults(String title) throws Exception {
        pageObject.clickFirstArticleLink(title);
    }
    
    @Then("I take a screenshot of the article page")
    public void iTakeAScreenshotOfTheArticlePage() throws Exception {
        pageObject.takeScreenshot("bdd-wikipedia-article-view-" + System.currentTimeMillis() + ".png");
    }
    
    @After
    public void tearDown() throws Exception {
        testManager.closeBrowser();
    }
}