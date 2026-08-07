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
        testManager.getPage().navigate(url);
    }
    
    @When("I execute step 1: {string}")
    public void executeStep1(String stepDescription) {
        System.out.println("📍 Step 1: " + stepDescription);
        pageObject.step1();
    }

    @When("I execute step 2: {string}")
    public void executeStep2(String stepDescription) {
        System.out.println("📍 Step 2: " + stepDescription);
        pageObject.step2();
    }

    @When("I execute step 3: {string}")
    public void executeStep3(String stepDescription) {
        System.out.println("📍 Step 3: " + stepDescription);
        pageObject.step3();
    }

    @When("I execute step 4: {string}")
    public void executeStep4(String stepDescription) {
        System.out.println("📍 Step 4: " + stepDescription);
        pageObject.step4();
    }

    @Then("the test should complete successfully")
    public void theTestShouldCompleteSuccessfully() throws Exception {
        pageObject.verifySearchResults();
        pageObject.verifyHTMLTutorials();
        pageObject.verifyNoErrorMessage();
        pageObject.takeScreenshot("bdd-screenshot-" + System.currentTimeMillis() + ".png");
        System.out.println("✅ Test completed successfully!");
    }
    
    @After
    public void tearDown() {
        testManager.closeBrowser();
    }
}