package com.automation.steps;

import io.cucumber.java.en.*;
import io.cucumber.java.Before;
import io.cucumber.java.After;
import com.automation.base.BaseTestManager;
import com.automation.pages.HTMLTutorialsPage;

public class HTMLTutorialsStepDefinitions {
    private BaseTestManager testManager;
    private HTMLTutorialsPage pageObject;
    
    @Before
    public void setUp() {
        testManager = new BaseTestManager();
        testManager.initializeBrowser();
        pageObject = new HTMLTutorialsPage(testManager);
    }
    
    @Given("I navigate to {string}")
    public void iNavigateTo(String url) {
        System.out.println("🌐 Navigating to: " + url);
        pageObject.navigateToW3Schools();
    }
    
    @When("I click on the search box")
    public void iClickOnTheSearchBox() {
        pageObject.clickSearchBox();
    }
    
    @When("I enter {string} in the search box")
    public void iEnterInTheSearchBox(String query) {
        pageObject.enterSearchQuery(query);
    }
    
    @When("I press Enter to submit the search")
    public void iPressEnterToSubmitTheSearch() {
        pageObject.pressEnterToSearch();
    }
    
    @Then("search results should be displayed")
    public void searchResultsShouldBeDisplayed() {
        pageObject.verifySearchResults();
    }
    
    @Then("HTML-related tutorials should be listed")
    public void htmlRelatedTutorialsShouldBeListed() {
        pageObject.verifyHTMLTutorials();
    }
    
    @Then("no error message should appear")
    public void noErrorMessageShouldAppear() {
        pageObject.verifyNoErrorMessage();
    }
    
    @After
    public void tearDown() {
        pageObject.takeScreenshot("bdd-screenshot-" + System.currentTimeMillis() + ".png");
        testManager.closeBrowser();
    }
}