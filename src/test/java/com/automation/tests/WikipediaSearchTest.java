package com.automation.tests;

import org.testng.annotations.Test;
import com.automation.base.BaseTestManager;
import com.automation.pages.WikipediaSearchPage;

public class WikipediaSearchTest extends BaseTestManager {
    
    @Test
    public void testWikipediaSearch() {
        System.out.println("✨ Starting Wikipedia Search Test");
        
        WikipediaSearchPage pageObject = new WikipediaSearchPage(this);
        
        pageObject.navigateToHomepage();
        pageObject.verifySearchBoxVisible();
        pageObject.enterSearchTerm("HTML");
        pageObject.verifySearchBoxValue("HTML");
        pageObject.clickSearchButton();
        pageObject.verifySearchResultsText("Search results for: HTML");
        pageObject.verifyMultipleSearchResults();
        pageObject.clickTopSearchResult();
        pageObject.verifyArticleContentVisible();
        
        pageObject.takeScreenshot("wikipedia-search-test-" + System.currentTimeMillis() + ".png");
        System.out.println("✅ Test completed successfully!");
    }
}