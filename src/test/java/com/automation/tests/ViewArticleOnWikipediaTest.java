package com.automation.tests;

import com.automation.base.BaseTestManager;
import com.automation.pages.WikipediaArticlePage;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class ViewArticleOnWikipediaTest extends BaseTestManager {

    @Test
    public void testViewArticleOnWikipedia() {
        WikipediaArticlePage pageObject = new WikipediaArticlePage(this);
        pageObject.navigateToWikipedia();
        pageObject.searchForArticle("Python programming language");
        pageObject.openArticle();
        pageObject.verifyArticlePageLoaded("Python (programming language) - Wikipedia");
        pageObject.takeScreenshot("wikipedia-article.png");
        assertTrue(java.nio.file.Paths.get("wikipedia-article.png").toFile().exists(), "Screenshot was not taken successfully");
    }
}