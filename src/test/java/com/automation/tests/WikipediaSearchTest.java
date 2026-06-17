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
        pageObject.verifySearchBoxVisible();
        
        pageObject.enterSearchTerm("Astronomy");
        pageObject.verifySearchBoxValue("Astronomy");
        
        pageObject.clickSearchButton();
        pageObject.verifySearchResultsVisible("Astronomy");
        
        pageObject.clickSearchResult("Astronomy");
        pageObject.verifyArticleContentVisible("Astronomy");
        pageObject.verifyUrlContains("/wiki/Astronomy");
        
        pageObject.takeScreenshot("testng-screenshot-" + System.currentTimeMillis() + ".png");
        System.out.println("✅ Test completed successfully!");
    }
}