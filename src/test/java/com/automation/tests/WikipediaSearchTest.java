package com.automation.tests;

import org.testng.annotations.Test;
import com.automation.base.BaseTestManager;
import com.automation.pages.WikipediaSearchPage;

public class WikipediaSearchTest extends BaseTestManager {
    
    @Test
    public void testWikipediaSearch() throws Exception {
        System.out.println("✨ Starting Wikipedia Search Test");
        
        WikipediaSearchPage pageObject = new WikipediaSearchPage(this);
        
        pageObject.navigateToWikipediaHomePage();
        pageObject.enterSearchTerm("HTML");
        pageObject.clickSearchButton();
        pageObject.clickSearchResult("HTML");
        
        pageObject.takeScreenshot("testng-screenshot-" + System.currentTimeMillis() + ".png");
        System.out.println("✅ Test completed successfully!");
    }
}