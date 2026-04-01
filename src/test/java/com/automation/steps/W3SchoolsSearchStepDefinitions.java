package com.automation.steps;

import io.cucumber.java.en.*;
import io.cucumber.java.Before;
import io.cucumber.java.After;
import com.automation.base.BaseTestManager;
import com.automation.pages.W3SchoolsSearchPage;

public class W3SchoolsSearchStepDefinitions {
    private BaseTestManager testManager;
    private W3SchoolsSearchPage pageObject;
    
    @Before
    public void setUp() {
        testManager = new BaseTestManager();
        testManager.initializeBrowser();
        pageObject = new W3SchoolsSearchPage(testManager);
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
    public void iEnterInTheSearchBox(String text) {
        pageObject.enterSearchText(text);
    }
    
    @When("I press Enter key to submit the search")
    public void iPressEnterKeyToSubmitTheSearch() {
        pageObject.pressEnterToSearch();
    }
    
    @Then("Search results should be displayed")
    public void searchResultsShouldBeDisplayed() {
        pageObject.verifySearchResults();
    }
    
    @Then("Results should contain HTML-related tutorials")
    public void resultsShouldContainHTMLRelatedTutorials() {
        pageObject.verifyHTMLTutorials();
    }
    
    @Then("No error or broken page should appear")
    public void noErrorOrBrokenPageShouldAppear() {
        pageObject.verifyNoErrorMessage();
    }
    
    @After
    public void tearDown() {
        pageObject.takeScreenshot("bdd-w3schools-search-" + System.currentTimeMillis() + ".png");
        System.out.println("✅ BDD Test completed successfully!");
        testManager.closeBrowser();
    }
}