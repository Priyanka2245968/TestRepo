package com.automation.tests;

import org.testng.annotations.Test;
import com.automation.base.BaseTestManager;
import com.automation.pages.WikipediaSearchPage;

public class WikipediaSearchTest extends BaseTestManager {
    
    @Test
    public void testWikipediaSearch() throws Exception {
        System.out.println("✨ Starting Wikipedia Search Test");
        
        WikipediaSearchPage pageObject = new WikipediaSearchPage(this);
        
        pageObject.navigateToWikipediaHomepage();
        pageObject.enterSearchQuery("Python programming");
        pageObject.pressEnterToSearch();
        pageObject.waitForTimeout(2000); // Wait for search results
        pageObject.clickSearchResult("Python (programming language)");
        pageObject.waitForURL("**/Python_(programming_language)**");
        
        pageObject.takeScreenshot("wikipedia-search-" + System.currentTimeMillis() + ".png");
        System.out.println("✅ Test completed successfully!");
    }
}