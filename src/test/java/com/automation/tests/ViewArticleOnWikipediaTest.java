package com.automation.tests;

import com.automation.base.BaseTestManager;
import com.automation.pages.ViewArticleOnWikipediaPage;
import org.testng.annotations.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class ViewArticleOnWikipediaTest extends BaseTestManager {

    @Test
    public void testNegativeOverlengthSearchTextShowsError() {
        System.out.println("✨ Starting Negative - Over-length search text shows error");
        ViewArticleOnWikipediaPage pageObject = new ViewArticleOnWikipediaPage(this);
        pageObject.navigateToWikipedia();
        pageObject.fillSearchField("A text longer than 500 characters");
        pageObject.clickSearchButton();
        assertThat(pageObject.getOverlengthSearchError()).isVisible();
        pageObject.takeScreenshot("overlength-search-error.png");
    }

    @Test
    public void testNegativeInvalidSearchTextShowsNoResults() {
        System.out.println("✨ Starting Negative - Invalid search text shows no results");
        ViewArticleOnWikipediaPage pageObject = new ViewArticleOnWikipediaPage(this);
        pageObject.navigateToWikipedia();
        pageObject.fillSearchField("invalid text string");
        pageObject.clickSearchButton();
        assertThat(pageObject.getNoResultsFoundMessage()).isVisible();
        pageObject.takeScreenshot("no-results-found.png");
    }
}