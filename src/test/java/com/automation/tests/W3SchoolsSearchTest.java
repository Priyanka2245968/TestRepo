package com.automation.tests;

import org.testng.annotations.Test;
import com.automation.base.BaseTestManager;
import com.automation.pages.W3SchoolsSearchPage;

public class W3SchoolsSearchTest extends BaseTestManager {
    
    @Test
    public void testW3SchoolsSearch() {
        System.out.println("✨ Starting W3Schools Search Test");
        
        W3SchoolsSearchPage pageObject = new W3SchoolsSearchPage(this);
        
        pageObject.navigateToW3Schools();
        pageObject.clickSearchBox();
        pageObject.enterSearchText("HTML");
        pageObject.pressEnterToSearch();
        
        pageObject.verifySearchResults();
        pageObject.verifyHTMLTutorials();
        pageObject.verifyNoErrorMessage();
        
        pageObject.takeScreenshot("w3schools-search-" + System.currentTimeMillis() + ".png");
        System.out.println("✅ Test completed successfully!");
    }
}