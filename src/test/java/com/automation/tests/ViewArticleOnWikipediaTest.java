package com.automation.tests;

import com.automation.base.BaseTestManager;
import com.automation.pages.ViewArticleOnWikipediaPage;
import com.microsoft.playwright.options.LoadState;
import org.testng.annotations.Test;

public class ViewArticleOnWikipediaTest extends BaseTestManager {

    @Test
    public void testHappyPathSearchAndViewWikipediaArticle() {
        ViewArticleOnWikipediaPage pageObject = new ViewArticleOnWikipediaPage(this);
        pageObject.navigateToWikipedia();
        pageObject.searchForArticle("HTML Tutorials");
        pageObject.verifySearchResultsPage();
        pageObject.clickTopSearchResult();
        pageObject.verifyArticlePageLoaded();
        pageObject.takeScreenshot("wikipedia-article.png");
    }

    @Test
    public void testWikipediaAccessibleWithoutLogin() {
        ViewArticleOnWikipediaPage pageObject = new ViewArticleOnWikipediaPage(this);
        pageObject.navigateToWikipedia();
        pageObject.verifyHomePageLoadedWithoutLogin();
        pageObject.takeScreenshot("wikipedia-home.png");
    }

    @Test
    public void testNegativeNoSearchTextProvided() {
        ViewArticleOnWikipediaPage pageObject = new ViewArticleOnWikipediaPage(this);
        pageObject.navigateToWikipedia();
        pageObject.clickSearchWithoutText();
        pageObject.verifyNoResultsMessage();
        pageObject.takeScreenshot("wikipedia-no-results.png");
    }

    @Test
    public void testNegativeSearchTextExceeds500Characters() {
        ViewArticleOnWikipediaPage pageObject = new ViewArticleOnWikipediaPage(this);
        pageObject.navigateToWikipedia();
        pageObject.fillSearchField("A string longer than 500 characters");
        pageObject.clickSearchButton();
        page.waitForLoadState(LoadState.NETWORKIDLE);
        pageObject.verifyErrorMessageDisplayed();
        pageObject.takeScreenshot("search_text_exceeds_500_chars.png");
    }

    @Test
    public void testNegativeSearchTextContainsInvalidQuery() {
        ViewArticleOnWikipediaPage pageObject = new ViewArticleOnWikipediaPage(this);
        pageObject.navigateToWikipedia();
        pageObject.fillSearchField("j0a92xjgljdfu!@#4");
        pageObject.clickSearchButton();
        page.waitForLoadState(LoadState.NETWORKIDLE);
        pageObject.verifyNoResultsMessageDisplayed();
        pageObject.takeScreenshot("invalid_search_query.png");
    }
}