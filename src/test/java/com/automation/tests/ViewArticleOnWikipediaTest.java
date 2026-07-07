package com.automation.tests;

import com.automation.base.BaseTestManager;
import com.automation.pages.ViewArticlePage;
import com.microsoft.playwright.options.LoadState;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ViewArticleOnWikipediaTest extends BaseTestManager {

        @Test
    public void testNegativeWhenVeryLongTextIsProvided() {
        ViewArticlePage pageObject = new ViewArticlePage(this);
        pageObject.navigateToWikipedia();
        String longText = pageObject.enterVeryLongTextInSearchField();
        pageObject.clickSearchButton();
        page.waitForLoadState(LoadState.NETWORKIDLE);
        String errorMessage = pageObject.verifyErrorMessageForLongSearch();
        Assert.assertTrue(errorMessage.contains("The search query is too long"));
        pageObject.takeScreenshot("long_search_error.png");
    }

        @Test
    public void testNegativeWhenInvalidTextIsProvided() {
        ViewArticlePage pageObject = new ViewArticlePage(this);
        pageObject.navigateToWikipedia();
        String invalidText = pageObject.enterInvalidTextInSearchField();
        pageObject.clickSearchButton();
        page.waitForLoadState(LoadState.NETWORKIDLE);
        String errorMessage = pageObject.verifyErrorMessageForInvalidSearch();
        Assert.assertTrue(errorMessage.contains("The search query could not be understood"));
        pageObject.takeScreenshot("invalid_search_error.png");
    }
}