package com.automation.tests;

import org.testng.annotations.Test;
import com.automation.base.BaseTestManager;
import com.automation.pages.LoginPage;

public class LoginTest extends BaseTestManager {

    @Test
    public void testLogin() throws Exception {
        System.out.println("✨ Starting Login Test");
        System.out.println("🌐 Navigating to: https://the-internet.herokuapp.com/login");

        LoginPage pageObject = new LoginPage(this);

        pageObject.step1();
        pageObject.step2();
        pageObject.step3();
        pageObject.step4();

        pageObject.takeScreenshot("testng-screenshot-" + System.currentTimeMillis() + ".png");
        System.out.println("✅ Test completed successfully!");
    }
}