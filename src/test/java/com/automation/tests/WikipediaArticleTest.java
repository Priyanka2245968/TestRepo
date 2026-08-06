package com.automation.tests;

import com.automation.base.BaseTestManager;
import com.automation.pages.WikipediaArticlePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class WikipediaArticleTest extends BaseTestManager {

    @Test
    public void testViewWikipediaArticleForSearchedTopic() {
        WikipediaArticlePage pageObject = new WikipediaArticlePage(this);
        pageObject.navigateToWikipedia();
        pageObject.searchForTerm("HTML Tutorial");
        pageObject.clickSearchButton();
        Assert.assertTrue(getPage().url().contains("HTML_Tutorial"), "Expected URL to contain 'HTML_Tutorial'");
        pageObject.takeScreenshot("wikipedia-search-result.png");
    }

    @Test
    public void testAnonymousAccessToWikipedia() {
        WikipediaArticlePage pageObject = new WikipediaArticlePage(this);
        pageObject.navigateToWikipedia();
        Assert.assertEquals(getPage().url(), "https://www.wikipedia.org/", "Expected URL to be Wikipedia homepage");
        pageObject.takeScreenshot("wikipedia-homepage.png");
    }

    @Test
    public void testNoSearchTextProvided() {
        WikipediaArticlePage pageObject = new WikipediaArticlePage(this);
        pageObject.navigateToWikipedia();
        pageObject.clickSearchButton();
        Assert.assertTrue(getPage().url().contains("Search"), "Expected URL to contain 'Search' when no search text is provided");
        pageObject.takeScreenshot("wikipedia-no-search-text.png");
    }
}