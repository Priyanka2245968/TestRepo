package com.automation.tests;

import com.automation.base.BaseTestManager;
import com.automation.pages.WikipediaArticlePage;
import com.microsoft.playwright.Locator;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ViewArticleTest extends BaseTestManager {

    @Test
    public void testViewArticle() {
        WikipediaArticlePage pageObject = new WikipediaArticlePage(this);
        pageObject.navigateToWikipedia();
        pageObject.searchForArticle("Python programming language");
        Assert.assertTrue(pageObject.isArticlePageLoaded("Python (programming language)"));
        Assert.assertTrue(pageObject.isTableOfContentsVisible());
        pageObject.takeScreenshot("article_page.png");
    }

    @Test
    public void testBlankSearch() {
        WikipediaArticlePage pageObject = new WikipediaArticlePage(this);
        pageObject.navigateToWikipedia();
        pageObject.searchForArticle("");
        Locator noResultsLocator = page.locator(".mw-search-nonefound");
        noResultsLocator.waitFor();
        Assert.assertTrue(noResultsLocator.isVisible());
        pageObject.takeScreenshot("blank_search.png");
    }

    @Test
    public void testLongQuery() {
        WikipediaArticlePage pageObject = new WikipediaArticlePage(this);
        String longQuery = "ThisIsAVeryLongArticleTitleThatExceedsTheMaximumLengthAllowedForAWikipediaArticleTitle";
        pageObject.navigateToWikipedia();
        pageObject.searchForArticle(longQuery);
        Locator searchResultsLocator = page.locator(".mw-search-results");
        searchResultsLocator.waitFor();
        Assert.assertTrue(searchResultsLocator.isVisible());
        pageObject.takeScreenshot("long_query.png");
    }
}