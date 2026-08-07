package com.automation.steps;

import io.cucumber.java.en.*;
import io.cucumber.java.Before;
import io.cucumber.java.After;
import com.automation.base.BaseTestManager;
import com.automation.pages.WikipediaSearchPage;

public class WikipediaSearchStepDefinitions {
    private BaseTestManager testManager;
    private WikipediaSearchPage pageObject;
    
    @Before
    public void setUp() throws Exception {
        testManager = new BaseTestManager();
        testManager.initializeBrowser();
        pageObject = new WikipediaSearchPage(testManager);
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