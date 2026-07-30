package com.automation.tests;

import com.automation.base.BaseTestManager;
import com.automation.pages.WikipediaArticlePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ViewArticleOnWikipediaTest extends BaseTestManager {

    @Test
    public void testViewHtmlTablesArticleOnWikipedia() {
        WikipediaArticlePage pageObject = new WikipediaArticlePage(this);
        pageObject.navigateToWikipedia();
        pageObject.searchForArticle("HTML Tables");
        pageObject.openFirstSearchResult();
        Assert.assertTrue(pageObject.isArticlePageLoaded(), "Article page did not load successfully");
        pageObject.takeScreenshot("html-tables-article.png");
    }

    @Test
    public void testWikipediaAccessibleToAnonymousUsers() {
        WikipediaArticlePage pageObject = new WikipediaArticlePage(this);
        pageObject.navigateToWikipedia();
        Assert.assertTrue(pageObject.isWikipediaHomePageLoaded(), "Wikipedia home page did not load successfully");
        pageObject.takeScreenshot("anonymous-access.png");
    }

    @Test
    public void testNoResultsForEmptySearch() {
        WikipediaArticlePage pageObject = new WikipediaArticlePage(this);
        pageObject.navigateToWikipedia();
        pageObject.searchForArticle("");
        Assert.assertTrue(pageObject.isNoSearchResultsDisplayed(), "Search results were displayed for an empty search");
        pageObject.takeScreenshot("no-search-results.png");
    }
}