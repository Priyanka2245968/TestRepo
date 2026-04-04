package com.automation.tests;

import com.automation.base.BaseTestManager;
import com.automation.pages.LoginPage;
import org.testng.annotations.Test;

public class LoginTest extends BaseTestManager {
    @Test
    public void testLoginFunctionality() {
        System.out.println("✨ Starting Login Functionality Test");

        LoginPage loginPage = new LoginPage(this);
        loginPage.navigateToLoginPage();
        loginPage.enterUsername("tomsmith");
        loginPage.enterPassword("SuperSecretPassword!");
        loginPage.clickLoginButton();

        System.out.println("✅ Test completed successfully!");
    }
}