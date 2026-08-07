package com.automation.steps;

import io.cucumber.java.en.*;
import com.automation.base.BaseTestManager;
import com.automation.pages.HTMLTutorialPage;

public class HTMLTutorialStepDefinitions {
    private BaseTestManager testManager;
    private HTMLTutorialPage pageObject;

    @Before
    public void setUp() {
        testManager = new BaseTestManager();
        testManager.initializeBrowser();
        pageObject = new HTMLTutorialPage(testManager);
    }

    @Given("I navigate to {string}")
    public void iNavigateTo(String url) {
        System.out.println("🌐 Navigating to: " + url);
        testManager.getPage().navigate(url);
    }

    @When("I click on the search box")
    public void iClickOnTheSearchBox() throws Exception {
        pageObject.clickSearchBox();
    }

    @When("I enter {string} in the search box")
    public void iEnterInTheSearchBox(String text) throws Exception {
        pageObject.enterSearchText(text);
    }

    @When("I press Enter key")
    public void iPressEnterKey() throws Exception {
        pageObject.pressEnterKey();
    }

    @Then("search results should be displayed")
    public void searchResultsShouldBeDisplayed() throws Exception {
        pageObject.verifySearchResults();
    }

    @Then("results should contain HTML-related tutorials")
    public void resultsShouldContainHTMLRelatedTutorials() throws Exception {
        pageObject.verifyHTMLTutorials();
    }

    @Then("no error or broken page should appear")
    public void noErrorOrBrokenPageShouldAppear() throws Exception {
        pageObject.verifyNoErrorMessage();
    }

    @After
    public void tearDown() {
        pageObject.takeScreenshot("bdd-screenshot-" + System.currentTimeMillis() + ".png");
        testManager.closeBrowser();
    }
}