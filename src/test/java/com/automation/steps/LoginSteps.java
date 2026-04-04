package com.automation.steps;

import com.automation.base.BaseTestManager;
import com.automation.pages.LoginPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

public class LoginSteps {
    private BaseTestManager testManager;
    private LoginPage loginPage;

    public LoginSteps(BaseTestManager testManager) {
        this.testManager = testManager;
        this.loginPage = new LoginPage(testManager);
    }

    @Given("I navigate to the login page")
    public void navigateToLoginPage() {
        loginPage.navigateToLoginPage();
    }

    @When("I enter username {string}")
    public void enterUsername(String username) {
        loginPage.enterUsername(username);
    }

    @When("I enter password {string}")
    public void enterPassword(String password) {
        loginPage.enterPassword(password);
    }

    @When("I click on the login button")
    public void clickLoginButton() {
        loginPage.clickLoginButton();
    }
}