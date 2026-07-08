package com.automation.tests;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import com.automation.base.BaseTestManager;
import com.automation.pages.ViewArticlePage;

public class ViewArticleTest extends BaseTestManager {

    @Test
    public void testNegativeSearchWithLongText() {
        ViewArticlePage pageObject = new ViewArticlePage(this);
        pageObject.navigateToWikipedia();
        pageObject.enterLongSearchText();
        pageObject.clickSearchButton();
        assertThat(pageObject.getErrorMessage()).containsText("Search request is longer than the maximum allowed length");
        pageObject.takeScreenshot("long_search_error.png");
    }

    @Test
    public void testNegativeSearchWithInvalidText() {
        ViewArticlePage pageObject = new ViewArticlePage(this);
        pageObject.navigateToWikipedia();
        pageObject.enterInvalidSearchText("asdfghjklzxcvbnm");
        pageObject.clickSearchButton();
        assertThat(getPage().locator(".mw-search-results")).isVisible();
        assertTrue(pageObject.getSearchResultsMessage().contains("There were no results matching the query"));
        pageObject.takeScreenshot("invalid_search_error.png");
    }
}
