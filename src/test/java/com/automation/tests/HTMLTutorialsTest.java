package com.automation.tests;

import org.testng.annotations.Test;
import com.automation.base.BaseTestManager;
import com.automation.pages.HTMLTutorialsPage;

public class HTMLTutorialsTest extends BaseTestManager {
    
    @Test
    public void testHTMLTutorials() throws Exception {
        System.out.println("✨ Starting HTML Tutorials Test");
        
        HTMLTutorialsPage pageObject = new HTMLTutorialsPage(this);
        
        pageObject.step1();
        pageObject.step2();
        pageObject.step3();
        pageObject.step4();
        
        pageObject.verifySearchResults();
        pageObject.verifyHTMLTutorials();
        pageObject.verifyNoErrorMessage();
        
        pageObject.takeScreenshot("testng-screenshot-" + System.currentTimeMillis() + ".png");
        System.out.println("✅ Test completed successfully!");
    }
}