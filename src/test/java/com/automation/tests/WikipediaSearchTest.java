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
        pageObject.typeInSearchBox("test");
        pageObject.clickSearchButton();
        pageObject.typeInSearchBox("Wikipedia");
        pageObject.clickSearchButton();
        pageObject.waitForSearchResults();
        pageObject.clickFirstLinkContainingText("Wikipedia");
        pageObject.waitForWikipediaArticleHeading();
        pageObject.scrollToContentsSection();
        pageObject.clickFirstLinkUnderContentsSection();
        pageObject.waitForNavigationToComplete();
        pageObject.goBackInBrowserHistory();
        pageObject.clickEditLink();
        
        pageObject.takeScreenshot("wikipedia-search-test-" + System.currentTimeMillis() + ".png");
        System.out.println("✅ Test completed successfully!");
    }
}