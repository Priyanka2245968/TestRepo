package com.automation.tests;

import com.automation.base.BaseTestManager;
import com.automation.pages.ViewArticlePage;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class ViewArticleTest extends BaseTestManager {

    @Test
    public void testHappyPath_SearchAndViewArticle() {
        ViewArticlePage pageObject = new ViewArticlePage(this);
        pageObject.navigateToWikipedia();
        pageObject.searchForArticle("Python programming language");
        pageObject.clickSearchButton();
        pageObject.clickArticleLink("Python (programming language)");
        Page page = getPage();
        page.waitForLoadState(LoadState.NETWORKIDLE);
        assertTrue(page.title().contains("Python (programming language) - Wikipedia"));
        pageObject.takeScreenshot("python-article.png");
    }

    @Test
    public void testNegative_BlankSearchField() {
        ViewArticlePage pageObject = new ViewArticlePage(this);
        pageObject.navigateToWikipedia();
        pageObject.clickSearchButton();
        String errorMessage = pageObject.getErrorMessage();
        assertTrue(errorMessage.contains("Please enter a search term"));
        pageObject.takeScreenshot("blank-search.png");
    }

    @Test
    public void testBoundary_MaximumSearchLength() {
        ViewArticlePage pageObject = new ViewArticlePage(this);
        