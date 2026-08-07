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
    
    @Then("the page at {string} loads successfully")
    public void thePageLoadsSuccessfully(String url) throws Exception {
        System.out.println("📍 The page at " + url + " loads successfully");
        pageObject.verifyPageLoadsSuccessfully();
    }
    
    @Then("the page heading {string} is displayed in the main content area")
    public void thePageHeadingIsDisplayedInTheMainContentArea(String heading) throws Exception {
        System.out.println("📍 And the page heading \"" + heading + "\" is displayed in the main content area");
        pageObject.verifyPageHeadingVisible();
    }
    
    @Then("the {string} is visible")
    public void theErrorMessageIsVisible(String target) throws Exception {
        System.out.println("📍 The " + target + " is visible");
        if (target.equals("error message")) {
            pageObject.verifyErrorMessageVisible();
        } else if (target.equals("message 'No tutorials found'")) {
            pageObject.verifyNoTutorialsFoundMessageVisible();
        }
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