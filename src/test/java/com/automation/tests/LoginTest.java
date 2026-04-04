package com.automation.tests;

import org.testng.annotations.Test;
import com.automation.base.BaseTestManager;
import com.automation.pages.LoginPage;

public class LoginTest extends BaseTestManager {

    @Test
    public void testSuccessfulLogin() throws Exception {
        LoginPage loginPage = new LoginPage(this);

        loginPage.navigateToLoginPage();
        loginPage.enterUsername("tomsmith");
        loginPage.enterPassword("SuperSecretPassword!");
        loginPage.clickLoginButton();

        // Add assertions here
        loginPage.takeScreenshot("login-success.png");
    }
}