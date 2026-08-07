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
    
    @When("I click the View HTML Tutorial link")
    public void iClickTheViewHTMLTutorialLink() throws Exception {
        pageObject.clickViewHTMLTutorialLink();
    }
    
    @When("I wait for the tutorial page to load")
    public void iWaitForTheTutorialPageToLoad() throws Exception {
        pageObject.waitForTutorialPageLoad();
    }
    
    @When("I take a screenshot for verification")
    public void iTakeAScreenshotForVerification() throws Exception {
        pageObject.takeScreenshot("bdd-screenshot-" + System.currentTimeMillis() + ".png");
    }
    
    @When("I verify the left navigation panel")
    public void iVerifyTheLeftNavigationPanel() throws Exception {
        pageObject.verifyLeftNavigationPanel();
    }
    
    @Then("the test should complete successfully")
    public void theTestShouldCompleteSuccessfully() throws Exception {
        System.out.println("✅ Test completed successfully!");
    }
    
    @After
    public void tearDown() throws Exception {
        testManager.closeBrowser();
    }
}