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
        pageObject.navigateToWikipediaHomepage();
    }
    
    @When("I enter {string} in the search box")
    public void iEnterInTheSearchBox(String query) throws Exception {
        pageObject.enterSearchQuery(query);
    }
    
    @When("I click the Search Wikipedia button")
    public void iClickTheSearchWikipediaButton() throws Exception {
        pageObject.clickSearchButton();
    }
    
    @When("I click the Python \\(programming language) link")
    public void iClickThePythonProgrammingLanguageLink() throws Exception {
        pageObject.clickPythonProgrammingLanguageLink();
    }
    
    @Then("I take a screenshot of the article page")
    public void iTakeAScreenshotOfTheArticlePage() throws Exception {
        pageObject.takeScreenshot("bdd-wikipedia-search-" + System.currentTimeMillis() + ".png");
    }
    
    @After
    public void tearDown() throws Exception {
        testManager.closeBrowser();
    }
}