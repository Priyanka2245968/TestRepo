package com.automation.steps;

import com.automation.base.BaseTestManager;
import com.automation.pages.ViewArticleOnWikipediaPage;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ViewArticleOnWikipediaStepDefinitions {
    private BaseTestManager testManager;
    private ViewArticleOnWikipediaPage pageObject;

    @Before
    public void setUp() throws Exception {
        testManager = new BaseTestManager();
        testManager.initializeBrowser();
        pageObject = new ViewArticleOnWikipediaPage(testManager);
    }

    @Given("I navigate to Wikipedia")
    public void iNavigateToWikipedia() throws Exception {
        pageObject.step1("Navigate to https://www.wikipedia.org/");
    }

    @When("I search for {string}")
    public void iSearchFor(String topic) throws Exception {
        pageObject.step2("Search for " + topic);
    }

    @When("I click on the {string} article link")
    public void iClickOnTheArticleLink(String articleTitle) throws Exception {
        pageObject.step3("Click on the " + articleTitle + " article link");
    }

    @Then("the {string} article page should load successfully")
    public void theArticlePageShouldLoadSuccessfully(String articleTitle) throws Exception {
        pageObject.step4("Verify the " + articleTitle + " article page loaded successfully");
    }

    @After
    public void tearDown() throws Exception {
        testManager.closeBrowser();
    }
}