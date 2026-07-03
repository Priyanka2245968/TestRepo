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
        assertTrue(pageObject.verifyLoginAndCreateAccountLinksVisible(), "Login and Create Account links are not visible");
        pageObject.clickCreateAccountLink();
        assertTrue(pageObject.verifyCreateAccountPageLoaded(), "Create Account page did not load successfully");
        pageObject.takeScreenshot("wikipedia-create-account.png");
    }

    @Test
    public void testNoSearchQueryProvided() {
        ViewArticleOnWikipediaPage pageObject = new ViewArticleOnWikipediaPage(this);
        pageObject.navigateToWikipedia();
        pageObject.clickSearchButton();
        assertTrue(pageObject.verifyNoSearchResultsDisplayed(), "Search results are displayed even without a search query");
    }
}