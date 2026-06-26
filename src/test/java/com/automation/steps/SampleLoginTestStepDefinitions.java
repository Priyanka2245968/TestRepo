package com.automation.steps;

import com.automation.base.BaseTestManager;
import com.automation.pages.SampleLoginTestPage;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

public class SampleLoginTestStepDefinitions {
    private BaseTestManager testManager;
    private SampleLoginTestPage sampleLoginPage;

    @Before
    public void setUp() throws Exception {
        testManager = new BaseTestManager();
        testManager.initializeBrowser();
        sampleLoginPage = new SampleLoginTestPage(testManager);
    }

    @Given("I navigate to {string}")
    public void iNavigateTo(String url) throws Exception {
        sampleLoginPage.step1();
    }

    @When("I execute step {int}: {string}")
    public void executeStep(int stepNum, String description) throws Exception {
        switch (stepNum) {
            case 1 -> sampleLoginPage.step1();
            case 2 -> sampleLoginPage.step2();
            case 3 -> sampleLoginPage.step3();
            case 4 -> sampleLoginPage.step4();
            case 5 -> sampleLoginPage.step5();
            case 6 -> sampleLoginPage.step6();
            default -> throw new Exception("Invalid step number: " + stepNum);
        }
    }

    @After
    public void tearDown() throws Exception {
        testManager.closeBrowser();
    }
}