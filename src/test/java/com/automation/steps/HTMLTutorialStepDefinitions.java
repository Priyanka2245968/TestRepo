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
    public void executeStep1(String desc) throws Exception {
        System.out.println("📍 Step 1: " + desc);
        pageObject.step1();
    }

    @When("I execute step 2: {string}")
    public void executeStep2(String desc) throws Exception {
        System.out.println("📍 Step 2: " + desc);
        pageObject.step2();
    }

    @When("I execute step 3: {string}")
    public void executeStep3(String desc) throws Exception {
        System.out.println("📍 Step 3: " + desc);
        pageObject.step3();
    }

    @When("I execute step 4: {string}")
    public void executeStep4(String desc) throws Exception {
        System.out.println("📍 Step 4: " + desc);
        pageObject.step4();
    }

    @When("I execute step 5: {string}")
    public void executeStep5(String desc) throws Exception {
        System.out.println("📍 Step 5: " + desc);
        pageObject.step5();
    }

    @When("I execute step 6: {string}")
    public void executeStep6(String desc) throws Exception {
        System.out.println("📍 Step 6: " + desc);
        pageObject.step6();
    }

    @When("I execute step 7: {string}")
    public void executeStep7(String desc) throws Exception {
        System.out.println("📍 Step 7: " + desc);
        pageObject.step7();
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