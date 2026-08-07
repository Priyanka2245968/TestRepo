package com.automation.tests;

import org.testng.annotations.Test;
import com.automation.base.BaseTestManager;
import com.automation.pages.HTMLTutorialPage;

public class HTMLTutorialTest extends BaseTestManager {
    
    @Test
    public void testSearchHTMLTutorials() {
        System.out.println("✨ Starting HTML Tutorial Search Test");
        
        HTMLTutorialPage pageObject = new HTMLTutorialPage(this);
        
        pageObject.navigateToW3Schools();
        pageObject.clickSearchBox();
        pageObject.enterSearchQuery("HTML");
        pageObject.pressEnterKey();
        
        pageObject.verifySearchResults();
        pageObject.verifyHTMLTutorials();
        pageObject.verifyNoErrorMessage();
        
        pageObject.takeScreenshot("html-tutorial-search-" + System.currentTimeMillis() + ".png");
        System.out.println("✅ Test completed successfully!");
    }
}