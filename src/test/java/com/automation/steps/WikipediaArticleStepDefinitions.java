package com.automation.steps;

import com.automation.base.BaseTestManager;
import com.automation.pages.WikipediaArticlePage;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class WikipediaArticleStepDefinitions {
    private BaseTestManager testManager;
    private WikipediaArticlePage pageObject;

    @Before
    public void setUp() throws Exception {
        testManager = new BaseTestManager();
        testManager.initializeBrowser();
        pageObject = new WikipediaArticlePage(testManager);
    }

    @Given("I navigate to {string}")
    public void iNavigateTo(String url) throws Exception {
        testManager.getPage().navigate(url);
    }

    @When("I execute step {string}")
    public void executeStep(String stepDescription) throws Exception {
        switch (stepDescription) {
            case "Enter 'HTML Tutorial' in the search field":
                pageObject.enterSearchQuery("HTML Tutorial");
                break;
            case "Click the 'Search' button":
                pageObject.clickSearchButton();
                break;
            case "Click the first search result link":
                pageObject.clickFirstSearchResult();
                break;
            default:
                throw new IllegalArgumentException("Invalid step description: " + stepDescription);
        }
    }

    @Then("I should see the {string} page")
    public void iShouldSeeThePage(String expectedTitle) {
        pageObject.verifyArticlePageLoaded(expectedTitle);
        Assert.assertTrue(testManager.getPage().url().contains(expectedTitle.replace(" ", "_")), "Article URL does not match expected title");
    }

    @After
    public void tearDown() {
        testManager.closeBrowser();
    }
}