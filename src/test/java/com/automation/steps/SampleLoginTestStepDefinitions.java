package com.automation.steps;

import com.automation.pages.SampleLoginTestPage;
import com.automation.base.BaseTestManager;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

public class SampleLoginTestStepDefinitions {
    private final BaseTestManager testManager;
    private final SampleLoginTestPage loginPage;

    public SampleLoginTestStepDefinitions(BaseTestManager testManager) {
        this.testManager = testManager;
        this.loginPage = new SampleLoginTestPage(testManager);
    }

    @When("I execute step {int}: {string}")
    public void executeStep(int stepNumber, String stepDescription) {
        switch (stepNumber) {
            case 1 -> loginPage.step1();
            case 2 -> loginPage.step2();
            case 3 -> loginPage.step3();
            case 4 -> loginPage.step4();
            case 5 -> loginPage.step5();
            case 6 -> loginPage.step6();
            default -> throw new IllegalArgumentException("Invalid step number: " + stepNumber);
        }
    }
}