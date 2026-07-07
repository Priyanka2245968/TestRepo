package com.automation.tests;

import com.automation.base.BaseTestManager;
import com.automation.pages.ViewArticlePage;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.options.LoadState;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ViewArticleTest extends BaseTestManager {

    @Test
    public void testHappyPath_ViewArticleSuccessfully() {
        ViewArticlePage pageObject = new ViewArticlePage(this);
        pageObject.navigateToWikipedia();
        pageObject.searchForArticle("Python programming language");
        pageObject.clickSearchButton();
        pageObject.clickArticleLink("Python (programming language)");
        page.waitForLoadState(LoadState.NETWORKIDLE);
        Assert.assertEquals(page.url(), "https://en.wikipedia.org/wiki/Python_(programming_language)");
        pageObject.takeScreenshot("article-page.png");
    }

    @Test
    public void testAccessWithoutAuthentication() {
        ViewArticlePage pageObject = new ViewArticlePage(this);
        pageObject.navigateToWikipedia();
        Assert.assertEquals(page.title(), "Wikipedia");
        pageObject.searchForArticle("Java programming language");
        pageObject.clickSearchButton();
        pageObject.clickArticleLink("Java (programming language)");
        page.waitForLoadState(LoadState.NETWORKIDLE);
        Assert.assertEquals(page.url(), "https://en.wikipedia.org/wiki/Java_(programming_language)");
    }
}