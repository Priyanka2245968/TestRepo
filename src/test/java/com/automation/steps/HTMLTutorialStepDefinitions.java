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
    public void executeStep1(String stepDescription) throws Exception {
        System.out.println("📍 Step 1: " + stepDescription);
        pageObject.step1();
    }

    @When("I execute step 2: {string}")
    public void executeStep2(String stepDescription) throws Exception {
        System.out.println("📍 Step 2: " + stepDescription);
        pageObject.step2();
    }

    @When("I execute step 3: {string}")
    public void executeStep3(String stepDescription) throws Exception {
        System.out.println("📍 Step 3: " + stepDescription);
        pageObject.step3();
    }

    @When("I execute step 4: {string}")
    public void executeStep4(String stepDescription) throws Exception {
        System.out.println("📍 Step 4: " + stepDescription);
        pageObject.step4();
    }

    @When("I execute step 5: {string}")
    public void executeStep5(String stepDescription) throws Exception {
        System.out.println("📍 Step 5: " + stepDescription);
        pageObject.step5();
    }

    @When("I execute step 6: {string}")
    public void executeStep6(String stepDescription) throws Exception {
        System.out.println("📍 Step 6: " + stepDescription);
        pageObject.step6();
    }

    @When("I execute step 7: {string}")
    public void executeStep7(String stepDescription) throws Exception {
        System.out.println("📍 Step 7: " + stepDescription);
        pageObject.step7();
    }

    @When("I execute step 8: {string}")
    public void executeStep8(String stepDescription) throws Exception {
        System.out.println("📍 Step 8: " + stepDescription);
        pageObject.step8();
    }

    @When("I execute step 9: {string}")
    public void executeStep9(String stepDescription) throws Exception {
        System.out.println("📍 Step 9: " + stepDescription);
        pageObject.step9();
    }

    @When("I execute step 10: {string}")
    public void executeStep10(String stepDescription) throws Exception {
        System.out.println("📍 Step 10: " + stepDescription);
        pageObject.step10();
    }

    @When("I execute step 11: {string}")
    public void executeStep11(String stepDescription) throws Exception {
        System.out.println("📍 Step 11: " + stepDescription);
        pageObject.step11();
    }

    @When("I execute step 12: {string}")
    public void executeStep12(String stepDescription) throws Exception {
        System.out.println("📍 Step 12: " + stepDescription);
        pageObject.step12();
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