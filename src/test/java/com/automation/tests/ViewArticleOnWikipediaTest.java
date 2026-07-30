package com.automation.tests;

import com.automation.base.BaseTestManager;
import com.automation.pages.ViewArticleOnWikipediaPage;
import com.microsoft.playwright.options.LoadState;
import org.testng.annotations.Test;

public class ViewArticleOnWikipediaTest extends BaseTestManager {

    @Test
    public void testHappyPathViewArticleOnWikipedia() {
        ViewArticleOnWikipediaPage pageObject = new ViewArticleOnWikipediaPage(this);
        pageObject.navigateToWikipedia();
        pageObject.fillSearchField("HTML Tables");
        pageObject.clickSearchButton();
        pageObject.waitForSearchResults();
        pageObject.clickFirstSearchResult();
        pageObject.waitForArticlePage();
        pageObject.takeScreenshot("happy-path-view-article.png");
    }

    @Test
    public void testWikipediaAccessibleToAnonymousUsers() {
        ViewArticleOnWikipediaPage pageObject = new ViewArticleOnWikipediaPage(this);
        pageObject.navigateToWikipedia();
        pageObject.waitForWikipediaHomePage();
        pageObject.takeScreenshot("anonymous-access.png");
    }

    @Test
    public void testNoArticleInSearchBar() {
        ViewArticleOnWikipediaPage pageObject = new ViewArticleOnWikipediaPage(this);
        pageObject.navigateToWikipedia();
        pageObject.clickSearchButton();
        pageObject.waitForNoSearchResults();
        pageObject.takeScreenshot("no-search-results.png");
    }

    @Test
    public void testViewArticleOnWikipedia() {
        WikipediaArticlePage pageObject = new WikipediaArticlePage(this);
        pageObject.navigateToWikipedia();
        pageObject.searchForArticle("HTML Table");
        pageObject.openFirstSearchResult();
        pageObject.takeScreenshot("wikipedia-article.png");
    }

    @Test
    public void testNegativeWhenVeryLongTextIsProvided() {
        WikipediaArticlePage pageObject = new WikipediaArticlePage(this);
        pageObject.navigateToWikipedia();
        String longText = "A string longer than 500 characters. " + "A".repeat(500);
        pageObject.searchForArticle(longText);
        pageObject.takeScreenshot("wikipedia-long-text.png");
    }

    @Test
    public void testNegativeWhenInvalidTextIsProvided() {
        WikipediaArticlePage pageObject = new WikipediaArticlePage(this);
        pageObject.navigateToWikipedia();
        pageObject.searchForArticle("jfkdlsjfkl93782!");
        pageObject.takeScreenshot("wikipedia-invalid-text.png");
    }
}