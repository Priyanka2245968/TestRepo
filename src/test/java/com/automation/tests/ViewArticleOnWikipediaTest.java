package com.automation.tests;

import com.automation.base.BaseTestManager;
import com.automation.pages.ViewArticleOnWikipediaPage;
import org.testng.annotations.Test;

public class ViewArticleOnWikipediaTest extends BaseTestManager {

    @Test
    public void testViewHtmlTablesArticleOnWikipedia() {
        ViewArticleOnWikipediaPage pageObject = new ViewArticleOnWikipediaPage(this);
        pageObject.navigateToWikipedia();
        pageObject.fillSearchField("HTML Tables");
        pageObject.clickSearchButton();
        pageObject.waitForSearchResults();
        pageObject.clickFirstSearchResult();
        pageObject.waitForArticlePage();
        pageObject.takeScreenshot("html-tables-article.png");
    }

    @Test
    public void testWikipediaAccessibleToAnonymousUsers() {
        ViewArticleOnWikipediaPage pageObject = new ViewArticleOnWikipediaPage(this);
        pageObject.navigateToWikipedia();
        pageObject.waitForWikipediaHomePage();
        pageObject.takeScreenshot("anonymous-access.png");
    }

    @Test
    public void testNoResultsForEmptySearch() {
        ViewArticleOnWikipediaPage pageObject = new ViewArticleOnWikipediaPage(this);
        pageObject.navigateToWikipedia();
        pageObject.fillSearchField("");
        pageObject.clickSearchButton();
        pageObject.waitForNoSearchResults();
        pageObject.takeScreenshot("no-search-results.png");
    }
}