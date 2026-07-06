package com.automation.tests;

import com.automation.base.BaseTestManager;
import com.automation.pages.ViewArticleOnWikipediaPage;
import com.microsoft.playwright.options.LoadState;
import org.testng.annotations.Test;

public class ViewArticleOnWikipediaTest extends BaseTestManager {

    @Test
    public void testHappyPathSearchAndViewArticle() {
        ViewArticleOnWikipediaPage pageObject = new ViewArticleOnWikipediaPage(this);
        pageObject.navigateToWikipediaHomepage();
        pageObject.searchForTerm("Python programming language");
        pageObject.clickSearchButton();
        pageObject.waitForSearchResultsToLoad();
        pageObject.clickPythonProgrammingLanguageLink();
        pageObject.waitForArticleToLoad();
        pageObject.verifyArticleContentVisible();
        pageObject.takeScreenshot("python-article.png");
    }

    @Test
    public void testNegativeBlankSearchInput() {
        ViewArticleOnWikipediaPage pageObject = new ViewArticleOnWikipediaPage(this);
        pageObject.navigateToWikipediaHomepage();
        pageObject.clickSearchButton();
        pageObject.waitForSearchResultsToLoad();
        pageObject.verifyNoResultsPageVisible();
        pageObject.takeScreenshot("no-results.png");
    }

    @Test
    public void testNegativeTooLongSearchInput() {
        ViewArticleOnWikipediaPage pageObject = new ViewArticleOnWikipediaPage(this);
        pageObject.navigateToWikipediaHomepage();
        String longSearchTerm = "This is a very long search term that exceeds the maximum length allowed by Wikipedia's search input field";
        pageObject.searchForTerm(longSearchTerm);
        pageObject.clickSearchButton();
        pageObject.waitForSearchResultsToLoad();
        pageObject.verifyNoResultsPageVisible();
        pageObject.takeScreenshot("long-search-term.png");
    }
}