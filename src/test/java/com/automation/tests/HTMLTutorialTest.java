package com.automation.tests;

import org.testng.annotations.Test;
import com.automation.base.BaseTestManager;
import com.automation.pages.HTMLTutorialPage;

public class HTMLTutorialTest extends BaseTestManager {
    
    @Test
    public void testHTMLTutorial() throws Exception {
        System.out.println("✨ Starting HTML Tutorial Test");
        
        HTMLTutorialPage pageObject = new HTMLTutorialPage(this);
        
        pageObject.step1();
        pageObject.verifyPageLoadsSuccessfully();
        pageObject.verifyPageHeadingVisible();
        pageObject.verifyErrorMessageVisible();
        pageObject.verifyNoTutorialsFoundMessageVisible();
        
        pageObject.takeScreenshot("testng-screenshot-" + System.currentTimeMillis() + ".png");
        System.out.println("✅ Test completed successfully!");
    }
}