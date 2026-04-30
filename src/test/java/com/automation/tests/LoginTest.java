package com.automation.tests;

import org.testng.annotations.Test;
import com.automation.base.BaseTestManager;
import com.automation.pages.LoginPage;

public class LoginTest extends BaseTestManager {

    @Test
    public void testLogin() throws Exception {
        LoginPage page = new LoginPage(this);

        page.step1();
        page.step2();
        page.step3();
        page.step4();

        page.takeScreenshot("testng-screenshot-" + System.currentTimeMillis() + ".png");
    }
}