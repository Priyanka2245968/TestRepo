package com.automation.tests;

import org.testng.annotations.Test;
import com.automation.base.BaseTestManager;
import com.automation.pages.HTMLTutorialsPage;

public class HTMLTutorialsTest extends BaseTestManager {
    
    @Test
    public void testSearchHTMLTutorials() {
        System.out.println("✨ Starting HTML Tutorials Search Test");
        
        HTMLTutorialsPage pageObject = new HTMLTutorialsPage(this);
        
        pageObject.navigateToW3Schools();
        pageObject.clickSearchBox();
        pageObject.enterSearchQuery("HTML");
        pageObject.pressEnterToSearch();
        
        pageObject.verifySearchResults();
        pageObject.verifyHTMLTutorials();
        pageObject.verifyNoErrorMessage();
        
        pageObject.takeScreenshot("testng-screenshot-" + System.currentTimeMillis() + ".png");
        System.out.println("✅ Test completed successfully!");
    }
}