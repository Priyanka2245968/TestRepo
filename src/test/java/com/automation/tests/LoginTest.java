package com.automation.tests;

import com.automation.base.BaseTestManager;
import com.automation.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTestManager {
    @Test
    public void testLoginFunctionality() {
        System.out.println("✨ Starting Login Functionality Test");
        System.out.println("🌐 Navigating to: https://the-internet.herokuapp.com");

        LoginPage loginPage = new LoginPage(this);
        loginPage.navigateToLoginPage();
        loginPage.enterUsername("tomsmith");
        loginPage.enterPassword("SuperSecretPassword!");
        loginPage.clickLoginButton();

        Assert.assertTrue(loginPage.isSuccessMessageDisplayed(), "Success message not displayed");
        System.out.println("✅ Test completed successfully!");
    }
}