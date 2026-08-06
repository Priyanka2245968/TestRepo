package com.automation.tests;

import com.automation.base.BaseTestManager;
import com.automation.pages.ViewArticleOnWikipediaPage;
import org.testng.annotations.Test;

public class ViewArticleOnWikipediaTest extends BaseTestManager {

    @Test
    public void testNegativeSearchTextExceeding500Characters() {
        System.out.println("✨ Starting Negative - Search text exceeding 500 characters");
        ViewArticleOnWikipediaPage pageObject = new ViewArticleOnWikipediaPage(this);
        pageObject.navigateToWikipedia();
        pageObject.fillSearchField("A text string with more than 500 characters");
        pageObject.clickSearchButton();
        pageObject.verifyErrorMessageDisplayed("An error has occurred while searching: Search request is longer than the maximum allowed length");
        pageObject.takeScreenshot("negative-search-text-exceeding-500-chars.png");
    }

    @Test
    public void testNegativeSearchTextWithNoMatchingResults() {
        System.out.println("✨ Starting Negative - Search text with no matching results");
        ViewArticleOnWikipediaPage pageObject = new ViewArticleOnWikipediaPage(this);
        pageObject.navigateToWikipedia();
        pageObject.fillSearchField("zxcvbnmasdfghjklqwertyuiop");
        pageObject.clickSearchButton();
        pageObject.verifyErrorMessageDisplayed("There were no results matching the query");
        pageObject.takeScreenshot("negative-search-text-no-matching-results.png");
    }
}