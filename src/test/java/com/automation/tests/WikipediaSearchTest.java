package com.automation.tests;

import org.testng.annotations.Test;
import com.automation.base.BaseTestManager;
import com.automation.pages.WikipediaSearchPage;

public class WikipediaSearchTest extends BaseTestManager {
    
    @Test
    public void testWikipediaSearch() {
        System.out.println("✨ Starting Wikipedia Search Test");
        
        WikipediaSearchPage page = new WikipediaSearchPage(this);
        
        page.navigateToWikipedia();
        page.enterSearchText("Python programming language");
        page.clickSearchButton();
        page.clickArticleLink("Python (programming language)");
        page.takeScreenshot("wikipedia-search-test-" + System.currentTimeMillis() + ".png");
        
        System.out.println("✅ Test completed successfully!");
    }
}