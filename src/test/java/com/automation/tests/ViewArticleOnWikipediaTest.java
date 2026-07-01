package com.automation.tests;

import com.automation.base.BaseTestManager;
import com.automation.pages.WikipediaArticlePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ViewArticleOnWikipediaTest extends BaseTestManager {

    @Test
    public void testViewArticleOnWikipedia() {
        String searchTerm = "Python programming language";
        String screenshotFilename = "wikipedia-article.png";

        WikipediaArticlePage pageObject = new WikipediaArticlePage(this);
        pageObject.navigateToWikipedia();
        pageObject.searchForArticle(searchTerm);
        pageObject.openArticle();
        pageObject.takeScreenshot(screenshotFilename);

        // Add assertions to verify the expected behavior
        String currentUrl = getPage().url();
        Assert.assertTrue(currentUrl.contains(searchTerm.replace(" ", "_")), "Article URL does not contain the search term");
    }

    @Override
    public void tearDown() {
        // Add any cleanup or teardown steps here
        super.tearDown();
    }
}