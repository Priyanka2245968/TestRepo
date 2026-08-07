package com.automation.tests;

import org.testng.annotations.Test;
import com.automation.base.BaseTestManager;
import com.automation.pages.HTMLTutorialPage;

public class HTMLTutorialTest extends BaseTestManager {

    @Test
    public void testHTMLTutorial() {
        System.out.println("✨ Starting HTML Tutorial Test");
        System.out.println("🌐 Navigating to: https://www.w3schools.com");

        HTMLTutorialPage pageObject = new HTMLTutorialPage(this);

        pageObject.navigateToW3Schools();
        pageObject.clickSearchBox();
        pageObject.enterSearchText("HTML");
        pageObject.pressEnterKey();

        pageObject.verifySearchResults();
        pageObject.verifyHTMLTutorials();
        pageObject.verifyNoErrorMessage();

        pageObject.takeScreenshot("testng-screenshot-" + System.currentTimeMillis() + ".png");
        System.out.println("✅ Test completed successfully!");
    }
}