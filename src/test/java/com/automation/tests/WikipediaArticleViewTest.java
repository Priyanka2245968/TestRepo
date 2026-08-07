package com.automation.tests;

import org.testng.annotations.Test;
import com.automation.base.BaseTestManager;
import com.automation.pages.WikipediaArticleViewPage;

public class WikipediaArticleViewTest extends BaseTestManager {
    
    @Test
    public void testViewWikipediaArticle() throws Exception {
        System.out.println("✨ Starting Wikipedia Article View Test");
        
        WikipediaArticleViewPage pageObject = new WikipediaArticleViewPage(this);
        
        pageObject.navigateToWikipediaHomepage();
        pageObject.verifySearchBoxVisible();
        
        pageObject.enterSearchQuery("Photosynthesis");
        pageObject.verifySearchBoxValue("Photosynthesis");
        
        pageObject.clickSearchButton();
        pageObject.verifySearchResultsVisible();
        pageObject.verifySearchResultsHeading("Search results for: Photosynthesis");
        
        pageObject.clickSearchResultLink("Anoxygenic_photosynthesis");
        
        pageObject.verifyArticlePageTitleVisible();
        pageObject.verifyArticleContentVisible();
        
        pageObject.takeScreenshot("testng-screenshot-" + System.currentTimeMillis() + ".png");
        System.out.println("✅ Test completed successfully!");
    }
}