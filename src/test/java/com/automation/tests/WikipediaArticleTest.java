package com.automation.tests;

import com.automation.base.BaseTestManager;
import com.automation.pages.WikipediaArticlePage;
import org.testng.annotations.Test;

public class WikipediaArticleTest extends BaseTestManager {

    @Test
    public void testViewWikipediaArticle() {
        WikipediaArticlePage pageObject = new WikipediaArticlePage(this);
        pageObject.navigateToWikipedia();
        pageObject.searchForTerm("Photosynthesis");
        pageObject.clickTopSearchResult();
        pageObject.verifyArticlePageLoaded();
        pageObject.takeScreenshot("wikipedia_article.png");
    }
}