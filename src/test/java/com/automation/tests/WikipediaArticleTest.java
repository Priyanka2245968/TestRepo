package com.automation.tests;

import com.automation.base.BaseTestManager;
import com.automation.pages.WikipediaArticlePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class WikipediaArticleTest extends BaseTestManager {

    @Test
    public void testSearchForArticleAndViewContent() {
        WikipediaArticlePage pageObject = new WikipediaArticlePage(this);
        pageObject.navigateToWikipedia();
        pageObject.searchForArticle("HTML Tables");
        pageObject.clickSearchButton();
        pageObject.clickFirstResultLink();
        // Add assertion to verify article content
        Assert.assertTrue(getPage().textContent().contains("HTML table"), "Article content does not contain 'HTML table'");
        pageObject.takeScreenshot("article-page.png");
    }

    @Test
    public void testWikipediaAccessibleAnonymously() {
        WikipediaArticlePage pageObject = new WikipediaArticlePage(this);
        pageObject.navigateToWikipedia();
        // Add assertion to verify Wikipedia homepage is loaded
        Assert.assertTrue(getPage().url().contains("wikipedia.org"), "Wikipedia homepage not loaded");
        pageObject.takeScreenshot("wikipedia-home.png");
    }

    @Test
    public void testNoArticleSearchShowsBlankPage() {
        WikipediaArticlePage pageObject = new WikipediaArticlePage(this);
        pageObject.navigateToWikipedia();
        pageObject.clickSearchButton();
        // Add assertion to verify no search results are shown
        Assert.assertTrue(getPage().textContent().contains("No results found"), "Search results found when no search term was provided");
        pageObject.takeScreenshot("no-search-results.png");
    }
}