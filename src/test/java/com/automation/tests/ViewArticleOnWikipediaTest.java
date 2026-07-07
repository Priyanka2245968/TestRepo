package com.automation.tests;

import com.automation.base.BaseTestManager;
import com.automation.pages.ViewArticleOnWikipediaPage;
import org.testng.annotations.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class ViewArticleOnWikipediaTest extends BaseTestManager {

    @Test
    public void testNegativeWhenVeryLongTextIsProvided() {
        ViewArticleOnWikipediaPage pageObject = new ViewArticleOnWikipediaPage(this);
        pageObject.navigateToWikipedia();
        pageObject.enterVeryLongTextInSearchField();
        pageObject.clickSearchButton();
        String errorMessage = pageObject.getErrorMessage();
        org.testng.Assert.assertTrue(errorMessage.contains("Search request is longer than the maximum allowed length"));
        pageObject.takeScreenshot("very-long-text-error.png");
    }

    @Test
    public void testNegativeWhenInvalidTextIsProvided() {
        ViewArticleOnWikipediaPage pageObject = new ViewArticleOnWikipediaPage(this);
        pageObject.navigateToWikipedia();
        pageObject.enterInvalidTextInSearchField();
        pageObject.clickSearchButton();
        String noResultsMessage = pageObject.getNoResultsMessage();
        org.testng.Assert.assertTrue(noResultsMessage.contains("There were no results matching the query"));
        pageObject.takeScreenshot("invalid-text-no-results.png");
    }
}