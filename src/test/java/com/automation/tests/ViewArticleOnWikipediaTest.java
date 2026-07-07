package com.automation.tests;

import com.automation.base.BaseTestManager;
import com.automation.pages.ViewArticleOnWikipediaPage;
import com.microsoft.playwright.options.LoadState;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class ViewArticleOnWikipediaTest extends BaseTestManager {

    @Test
    public void testNegativeWhenVeryLongTextIsProvided() {
        ViewArticleOnWikipediaPage pageObject = new ViewArticleOnWikipediaPage(this);
        pageObject.navigateToWikipedia();
        String longText = pageObject.enterVeryLongTextInSearchField();
        pageObject.clickSearchButton();
        String errorMessage = pageObject.verifyErrorMessageForLongSearch();
        assertTrue(errorMessage.contains("The search query is too long"));
        pageObject.takeScreenshot("long_search_error.png");
    }

    @Test
    public void testNegativeWhenInvalidTextIsProvided() {
        ViewArticleOnWikipediaPage pageObject = new ViewArticleOnWikipediaPage(this);
        pageObject.navigateToWikipedia();
        String invalidText = pageObject.enterInvalidTextInSearchField();
        pageObject.clickSearchButton();
        String errorMessage = pageObject.verifyErrorMessageForInvalidSearch();
        assertTrue(errorMessage.contains("The search query could not be understood"));
        pageObject.takeScreenshot("invalid_search_error.png");
    }
}
