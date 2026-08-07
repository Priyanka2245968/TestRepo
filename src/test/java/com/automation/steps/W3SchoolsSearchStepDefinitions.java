package com.automation.steps;

import io.cucumber.java.en.*;
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
        pageObject.navigateToW3Schools();
    }

    @When("I click on the search input box at the top")
    public void iClickOnTheSearchInputBoxAtTheTop() {
        pageObject.clickSearchBox();
    }

    @When("I enter the keyword {string} in the search box")
    public void iEnterTheKeywordInTheSearchBox(String keyword) {
        pageObject.enterSearchText(keyword);
    }

    @When("I press Enter key to submit the search")
    public void iPressEnterKeyToSubmitTheSearch() {
        pageObject.pressEnterToSearch();
    }

    @Then("Search results should be displayed")
    public void searchResultsShouldBeDisplayed() {
        pageObject.verifySearchResultsVisible();
    }

    @Then("Results should contain HTML-related tutorials")
    public void resultsShouldContainHTMLRelatedTutorials() {
        pageObject.verifyHTMLTutorialsPresent();
    }

    @Then("No error or broken page should appear")
    public void noErrorOrBrokenPageShouldAppear() {
        pageObject.verifyNoErrorMessage();
    }

    @After
    public void tearDown() {
        pageObject.takeScreenshot("bdd-screenshot-" + System.currentTimeMillis() + ".png");
        testManager.closeBrowser();
    }
}