package com.automation.steps;

import com.automation.base.BaseTestManager;
import com.automation.pages.LoginPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

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

    @When("I enter username {string} in the username field")
    public void enterUsername(String username) {
        loginPage.enterUsername(username);
    }

    @When("I enter password {string} in the password field")
    public void enterPassword(String password) {
        loginPage.enterPassword(password);
    }

    @When("I click on the login button")
    public void clickLoginButton() {
        loginPage.clickLoginButton();
    }

    @Then("I should see the success message")
    public void verifySuccessMessage() {
        Assert.assertTrue(loginPage.isSuccessMessageDisplayed(), "Success message not displayed");
    }
}