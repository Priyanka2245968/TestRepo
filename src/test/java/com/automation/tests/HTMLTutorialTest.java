package com.automation.tests;

import org.testng.annotations.Test;
import com.automation.base.BaseTestManager;
import com.automation.pages.HTMLTutorialPage;

public class HTMLTutorialTest extends BaseTestManager {
    
    @Test
    public void testViewHTMLTutorial() throws Exception {
        System.out.println("✨ Starting [Happy Path] View HTML Tutorial on W3Schools");
        
        HTMLTutorialPage pageObject = new HTMLTutorialPage(this);
        
        pageObject.step1();
        pageObject.verifySearchBoxVisible();
        pageObject.step2();
        pageObject.step3();
        pageObject.verifySearchResultsCount();
        pageObject.verifyTutorialPageURL();
        pageObject.verifyTutorialHeading();
        pageObject.verifyLeftNavHighlighted();
        pageObject.verifyTutorialContent();
        pageObject.verifyNoErrorsVisible();
        
        pageObject.takeScreenshot("testng-screenshot-" + System.currentTimeMillis() + ".png");
        System.out.println("✅ Test completed successfully!");
    }
}