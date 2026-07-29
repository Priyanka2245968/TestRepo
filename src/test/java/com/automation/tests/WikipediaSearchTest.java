package com.automation.tests;

import org.testng.annotations.Test;
import com.automation.base.BaseTestManager;
import com.automation.pages.WikipediaSearchPage;

public class WikipediaSearchTest extends BaseTestManager {
    
    @Test
    public void testWikipediaSearch() {
        WikipediaSearchPage page = new WikipediaSearchPage(this);
        
        page.navigateToWikipediaHomepage();
        page.enterSearchTerm("HTML");
        page.clickSearchButton();
        page.clickTopSearchResult();
        
        page.takeScreenshot("wikipedia-search-test.png");
    }
}