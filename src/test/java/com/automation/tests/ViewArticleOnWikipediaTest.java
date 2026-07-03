package com.automation.tests;

import com.automation.base.BaseTestManager;
import com.automation.pages.ViewArticleOnWikipediaPage;
import com.microsoft.playwright.options.LoadState;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class ViewArticleOnWikipediaTest extends BaseTestManager {

    @Test
    public void testHappyPathViewArticleOnWikipedia() {
        ViewArticleOnWikipediaPage pageObject = new ViewArticleOnWikipediaPage(this);
        pageObject.navigateToWikipedia();
        pageObject.searchForPythonProgrammingLanguage();
        pageObject.verifySearchResultsHeading();
        pageObject.clickPythonProgrammingLanguageLink();
        pageObject.verifyArticlePageLoaded();
        pageObject.takeScreenshot("wikipedia-python-article.png");
    }

    @Test
    public void testAnonymousAccessToWikipedia() {
        ViewArticleOnWikipediaPage pageObject = new ViewArticleOnWikipediaPage(this);
        pageObject.navigateToWikipedia();
        pageObject.verifyLoginAndCreateAccountLinksVisible();
        pageObject.clickCreateAccountLink();
        pageObject.verifyCreateAccountPageLoaded();
        pageObject.takeScreenshot("wikipedia-create-account.png");
    }

    @Test
    public void testNoSearchQueryProvided() {
        ViewArticleOnWikipediaPage pageObject = new ViewArticleOnWikipediaPage(this);
        pageObject.navigateToWikipedia();
        pageObject.clickSearchButtonWithoutQuery();
        pageObject.verifyNoResultsMessageDisplayed();
        pageObject.takeScreenshot("wikipedia-no-search-query.png");
    }

    @Test
    public void testNegativeLongSearchQueryExceeding500Characters() {
        ViewArticleOnWikipediaPage pageObject = new ViewArticleOnWikipediaPage(this);
        pageObject.navigateToWikipediaHomepage();
        pageObject.enterLongSearchQuery();
        pageObject.clickSearchButton();
        assertTrue(pageObject.getErrorMessage().contains("Search request is longer than the maximum allowed length"));
        pageObject.takeScreenshot("long_search_query_error.png");
    }

    @Test
    public void testNegativeInvalidSearchQueryWithNoMatchingArticles() {
        ViewArticleOnWikipediaPage pageObject = new ViewArticleOnWikipediaPage(this);
        pageObject.navigateToWikipediaHomepage();
        pageObject.enterInvalidSearchQuery("asdfghjklqwertyuiop");
        pageObject.clickSearchButton();
        assertTrue(pageObject.getErrorMessage().contains("There were no results matching the query"));
        pageObject.takeScreenshot("invalid_search_query_error.png");
    }
}
