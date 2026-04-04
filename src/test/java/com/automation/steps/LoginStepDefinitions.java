package com.automation.steps;

import io.cucumber.java.en.*;
import com.automation.base.BaseTestManager;
import com.automation.pages.LoginPage;

public class LoginStepDefinitions {
    private BaseTestManager testManager;
    private LoginPage loginPage;

    @Before
    public void setUp() {
        testManager = new BaseTestManager();
        testManager.initializeBrowser();
        loginPage = new LoginPage(testManager);
    }

    @Given("I navigate to the login page")
    public void navigateToLoginPage() throws Exception {
        loginPage.navigateToLoginPage();
    }

    @When("I enter username {string}")
    public void enterUsername(String username) throws Exception {
        loginPage.enterUsername(username);
    }

    @When("I enter password {string}")
    public void enterPassword(String password) throws Exception {
        loginPage.enterPassword(password);
    }

    @When("I click the Login button")
    public void clickLoginButton() throws Exception {
        loginPage.clickLoginButton();
    }

    @After
    public void tearDown() {
        testManager.closeBrowser();
    }
}