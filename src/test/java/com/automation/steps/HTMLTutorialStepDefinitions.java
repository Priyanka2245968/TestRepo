package com.automation.steps;

import io.cucumber.java.en.*;
import io.cucumber.java.Before;
import io.cucumber.java.After;
import com.automation.base.BaseTestManager;
import com.automation.pages.HTMLTutorialPage;

public class HTMLTutorialStepDefinitions {
    private BaseTestManager testManager;
    private HTMLTutorialPage pageObject;
    
    @Before
    public void setUp() throws Exception {
        testManager = new BaseTestManager();
        testManager.initializeBrowser();
        pageObject = new HTMLTutorialPage(testManager);
    }
    
    @Given("I navigate to {string}")
    public void iNavigateTo(String url) throws Exception {
        System.out.println("🌐 Navigating to: " + url);
        testManager.getPage().navigate(url);
    }
    
    @When("I execute step 1: {string}")
    public void executeStep1(String description) throws Exception {
        System.out.println("📍 Step 1: " + description);
        pageObject.step1();
    }
    
    @When("I execute step 2: {string}")
    public void executeStep2(String description) throws Exception {
        System.out.println("📍 Step 2: " + description);
        pageObject.step2();
    }
    
    @When("I execute step 3: {string}")
    public void executeStep3(String description) throws Exception {
        System.out.println("📍 Step 3: " + description);
        pageObject.step3();
    }
    
    @When("I execute step 4: {string}")
    public void executeStep4(String description) throws Exception {
        System.out.println("📍 Step 4: " + description);
        pageObject.step4();
    }
    
    @Then("Search results should be displayed")
    public void searchResultsShouldBeDisplayed() throws Exception {
        pageObject.verifySearchResults();
    }
    
    @Then("Results should contain HTML-related tutorials")
    public void resultsShouldContainHTMLRelatedTutorials() throws Exception {
        pageObject.verifyHTMLTutorials();
    }
    
    @Then("No error or broken page should appear")
    public void noErrorOrBrokenPageShouldAppear() throws Exception {
        pageObject.verifyNoErrorMessage();
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