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
        System.out.println("📍 Click the Search button next to the search field");
        pageObject.clickSearchButton();
    }
    
    @When("I click the {string} link")
    public void iClickTheLink(String linkText) throws Exception {
        System.out.println("📍 Click the '" + linkText + "' link under the 'Did you mean:' section");
        pageObject.clickHTMLLink();
    }
    
    @Then("I take a screenshot")
    public void iTakeAScreenshot() throws Exception {
        System.out.println("📍 Take a screenshot of the HTML article page");
        pageObject.takeScreenshot("bdd-wikipedia-search-test-" + System.currentTimeMillis() + ".png");
    }
    
    @After
    public void tearDown() throws Exception {
        testManager.closeBrowser();
    }
}