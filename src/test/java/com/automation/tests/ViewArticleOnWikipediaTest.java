package com.automation.tests;

import com.automation.base.BaseTestManager;
import com.automation.pages.WikipediaArticlePage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class ViewArticleOnWikipediaTest extends BaseTestManager {

    @Test
    public void testViewArticleOnWikipedia() {
        String searchTerm = "Python programming language";
        String screenshotFilename = "wikipedia-article.png";

        WikipediaArticlePage pageObject = new WikipediaArticlePage(this);
        pageObject.navigateToWikipedia();
        pageObject.searchForArticle(searchTerm);
        pageObject.openArticle(searchTerm.replace(" ", "_"));
        pageObject.takeScreenshot(screenshotFilename);

        assertTrue(page.url().contains(searchTerm.replace(" ", "_")));
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        closeBrowser();
    }
}