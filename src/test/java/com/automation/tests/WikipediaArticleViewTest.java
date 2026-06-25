package com.automation.tests;

import org.testng.annotations.Test;
import com.automation.base.BaseTestManager;
import com.automation.pages.WikipediaArticleViewPage;

public class WikipediaArticleViewTest extends BaseTestManager {
    
    @Test
    public void testViewWikipediaArticle() throws Exception {
        System.out.println("✨ Starting Wikipedia Article View Test");
        System.out.println("🌐 Navigating to: https://www.wikipedia.org/");
        
        WikipediaArticleViewPage pageObject = new WikipediaArticleViewPage(this);
        
        page.navigate("https://www.wikipedia.org/");
        
        pageObject.verifySearchBoxVisible();
        pageObject.enterSearchText("Photosynthesis");
        pageObject.verifySearchBoxValue("Photosynthesis");
        pageObject.clickSearchButton();
        pageObject.verifySearchResultsVisible();
        pageObject.verifySearchResultsHeading("Photosynthesis");
        pageObject.clickArticleLink("Photosynthesis_(disambiguation)");
        pageObject.verifyArticleTitleVisible();
        pageObject.verifyArticleContentVisible();
        
        pageObject.takeScreenshot("testng-screenshot-" + System.currentTimeMillis() + ".png");
        System.out.println("✅ Test completed successfully!");
    }
}