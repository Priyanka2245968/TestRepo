package com.automation.tests;

import org.testng.annotations.Test;

import com.automation.base.BaseTestManager;
import com.automation.pages.ViewArticlePage;

import static org.testng.Assert.assertTrue;

public class ViewArticleTest extends BaseTestManager {

    @Test
    public void testNegativeSearchWithLongText() {
        ViewArticlePage pageObject = new ViewArticlePage(this);
        pageObject.navigateToWikipedia();
        pageObject.enterLongSearchText();
        pageObject.clickSearchButton();
        pageObject.waitForErrorMessage();
        assertTrue(pageObject.getErrorMessage().contains("Search request is longer than the maximum allowed length"));
        pageObject.takeScreenshot("long_search_error.png");
    }

    @Test
    public void testNegativeSearchWithInvalidText() {
        ViewArticlePage pageObject = new ViewArticlePage(this);
        pageObject.navigateToWikipedia();
        pageObject.enterInvalidSearchText("asdfghjklzxcvbnm");
        pageObject.clickSearchButton();
        pageObject.waitForSearchResults();
        assertTrue(pageObject.getNoResultsMessage().contains("There were no results matching the query"));
        pageObject.takeScreenshot("invalid_search_error.png");
    }
}