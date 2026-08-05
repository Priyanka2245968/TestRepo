package com.automation.tests;

import org.testng.annotations.Test;
import com.automation.base.BaseTestManager;
import com.automation.pages.WikipediaArticleViewPage;

public class WikipediaArticleViewTest extends BaseTestManager {

    @Test
    public void testViewWikipediaArticle() {
        System.out.println("✨ Starting Wikipedia Article View Test");

        WikipediaArticleViewPage pageObject = new WikipediaArticleViewPage(this);

        pageObject.navigateToWikipediaHomepage();
        pageObject.enterSearchText("HTML Tutorial");
        pageObject.clickSearchButton();
        pageObject.clickFirstSearchResult();
        pageObject.takeScreenshot("wikipedia-article-view-" + System.currentTimeMillis() + ".png");

        System.out.println("✅ Test completed successfully!");
    }
}