package com.automation.tests;

import com.automation.base.BaseTestManager;
import com.automation.pages.WikipediaArticlePage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class ViewArticleOnWikipediaTest extends BaseTestManager {

    @Test
    public void testViewArticleOnWikipedia() {
        WikipediaArticlePage pageObject = new WikipediaArticlePage(this);
        pageObject.navigateToWikipedia();
        pageObject.searchForArticle("Python programming language");
        pageObject.openArticle();
        assertTrue(pageObject.isArticleDisplayed("Python (programming language)"));
        pageObject.takeScreenshot("wikipedia-article.png");
    }

    @AfterMethod
    public void tearDown() {
        closeBrowser();
    }
}