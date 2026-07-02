package com.automation.apiTests;

import org.testng.annotations.Test;
import com.automation.base.BaseTestManager;
import com.automation.pages.WikipediaArticleViewPage;

public class WikipediaArticleViewTest extends BaseTestManager {
    
    @Test
    public void testViewWikipediaArticle() throws Exception {
        System.out.println("✨ Starting Wikipedia Article View Test");
        
        WikipediaArticleViewPage pageObject = new WikipediaArticleViewPage(this);
        
        pageObject.navigateToWikipediaHomepage();
        pageObject.enterSearchText("Machine Learning");
        pageObject.clickSearchButton();
        pageObject.clickFirstArticleLink("Machine Learning");
        pageObject.takeScreenshot("wikipedia-article-view-" + System.currentTimeMillis() + ".png");
        
        System.out.println("✅ Test completed successfully!");
    }
}