package com.automation.steps;

import io.cucumber.java.en.*;
import io.cucumber.java.Before;
import io.cucumber.java.After;
import com.automation.base.BaseTestManager;
import com.automation.pages.LoginPage;

public class LoginStepDefinitions {
    private BaseTestManager testManager;
    private LoginPage pageObject;

    @Before
    public void setUp() throws Exception {
        testManager = new BaseTestManager();
        testManager.initializeBrowser();
        pageObject = new LoginPage(testManager);
    }

    @Given("I navigate to {string}")
    public void iNavigateTo(String url) throws Exception {
        System.out.println("🌐 Navigating to: " + url);
        testManager.getPage().navigate(url);
    }

    @When("I enter username {string} in the username field")
    public void iEnterUsernameInTheUsernameField(String username) throws Exception {
        System.out.println("📍 Enter username in the username field");
        pageObject.enterUsername(username);
    }

    @When("I enter password {string} in the password field")
    public void iEnterPasswordInThePasswordField(String password) throws Exception {
        System.out.println("📍 Enter password in the password field");
        pageObject.enterPassword(password);
    }

    @When("I click the Login button")
    public void iClickTheLoginButton() throws Exception {
        System.out.println("📍 Click the Login button");
        pageObject.clickLoginButton();
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