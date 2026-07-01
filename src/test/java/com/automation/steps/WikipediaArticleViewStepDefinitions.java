package com.automation.steps;

import io.cucumber.java.en.*;
import io.cucumber.java.Before;
import io.cucumber.java.After;
import com.automation.base.BaseTestManager;
import com.automation.pages.WikipediaArticleViewPage;

public class WikipediaArticleViewStepDefinitions {
    private BaseTestManager testManager;
    private WikipediaArticleViewPage pageObject;

    @Before
    public void setUp() throws Exception {
        testManager = new BaseTestManager();
        testManager.initializeBrowser();
        pageObject = new WikipediaArticleViewPage(testManager);
    }

    @Given("I navigate to {string}")
    public void iNavigateTo(String url) throws Exception {
        System.out.println("🌐 Navigating to: " + url);
        testManager.getPage().navigate(url);
    }

    @When("I execute step {int}: {string}")
    public void executeStep(int stepNumber, String description) throws Exception {
        System.out.println("📍 Step " + stepNumber + ": " + description);
        switch (stepNumber) {
            case 1 -> pageObject.step1();
            case 2 -> pageObject.step2();
            case 3 -> pageObject.step3();
            case 4 -> pageObject.step4();
            case 5 -> pageObject.step5();
            default -> throw new IllegalArgumentException("Invalid step number: " + stepNumber);
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