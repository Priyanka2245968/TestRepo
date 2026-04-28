package com.automation.tests;

import org.testng.annotations.Test;
import com.automation.base.BaseTestManager;
import com.automation.pages.HTMLTutorialPage;

public class HTMLTutorialTest extends BaseTestManager {
    
    @Test
    public void testViewHTMLTutorial() throws Exception {
        System.out.println("✨ Starting View HTML Tutorial Test");
        
        HTMLTutorialPage pageObject = new HTMLTutorialPage(this);
        
        pageObject.step1();
        pageObject.step2();
        pageObject.step3();
        
        pageObject.verifyHTMLTutorialPageLoaded();
        pageObject.verifyHTMLTutorialHeadingDisplayed();
        pageObject.verifyHTMLHomeHighlighted();
        
        pageObject.takeScreenshot("testng-screenshot-" + System.currentTimeMillis() + ".png");
        System.out.println("✅ Test completed successfully!");
    }
}