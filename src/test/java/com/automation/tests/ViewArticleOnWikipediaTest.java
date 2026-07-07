package com.automation.tests;

import com.automation.base.BaseTestManager;
import com.automation.pages.ViewArticleOnWikipediaPage;
import com.microsoft.playwright.options.LoadState;
import org.testng.annotations.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.testng.Assert.assertTrue;

public class ViewArticleOnWikipediaTest extends BaseTestManager {

    @Test
    public void testNegativeSearchInputExceedsMaxLength() {
        ViewArticleOnWikipediaPage pageObject = new ViewArticleOnWikipediaPage(this);
        pageObject.navigateToWikipedia();
        pageObject.enterSearchText("A string longer than 500 characters. This is a very long string that exceeds the maximum allowed length for a Wikipedia search query. It is used to test the behavior of the Wikipedia search engine when an input string exceeds the maximum allowed length. This string is intentionally made very long to ensure that it exceeds the maximum allowed length for a Wikipedia search query.");
        pageObject.clickSearchButton();
        pageObject.waitForErrorMessage();
        assertTrue(pageObject.getErrorMessage().contains("An error has occurred while searching: Search request is longer than the maximum allowed length"));
        pageObject.takeScreenshot("negative-search-input-exceeds-max-length.png");
    }

    @Test
    public void testNegativeSearchInputHasNoMatches() {
        ViewArticleOnWikipediaPage pageObject = new ViewArticleOnWikipediaPage(this);
        pageObject.navigateToWikipedia();
        pageObject.enterSearchText("asgcnbdwhjssnjjsb");
        pageObject.clickSearchButton();
        pageObject.waitForNoResultsMessage();
        assertTrue(pageObject.getNoResultsMessage().contains("There were no results matching the query"));
        pageObject.takeScreenshot("negative-search-input-no-matches.png");
    }
}
