package com.automation.apiTests;

import org.testng.annotations.Test;
import com.automation.base.BaseTestManager;
import com.automation.pages.LoginPage;

public class LoginTest extends BaseTestManager {

    @Test
    public void testLogin() throws Exception {
        System.out.println("✨ Starting Login Test");
        System.out.println("🌐 Navigating to: https://the-internet.herokuapp.com");

        LoginPage pageObject = new LoginPage(this);

        pageObject.navigateToLoginPage();
        pageObject.enterUsername("tomsmith");
        pageObject.enterPassword("SuperSecretPassword!");
        pageObject.clickLoginButton();

        pageObject.takeScreenshot("testng-screenshot-" + System.currentTimeMillis() + ".png");
        System.out.println("✅ Test completed successfully!");
    }
}