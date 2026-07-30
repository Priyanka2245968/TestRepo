package com.automation.tests;

import org.testng.annotations.Test;
import com.automation.base.BaseTestManager;
import com.automation.pages.ViewArticleOnWikipediaPage;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class ViewArticleOnWikipediaTest extends BaseTestManager {

    @Test
    public void testNegative_WhenVeryLongTextIsProvidedInSearchBar() {
        System.out.println("✨ Starting Negative - When a very long text is provided in the search bar");
        ViewArticleOnWikipediaPage pageObject = new ViewArticleOnWikipediaPage(this);
        pageObject.navigateToWikipedia();
        pageObject.fillSearchField("A string longer than 500 characters");
        pageObject.clickSearchButton();
        pageObject.verifyErrorMessageForLongSearch();
        pageObject.takeScreenshot("long-search-error.png");
    }

    @Test
    public void testNegative_WhenInvalidTextIsProvided() {
        System.out.println("✨ Starting Negative - When an invalid text is provided");
        ViewArticleOnWikipediaPage pageObject = new ViewArticleOnWikipediaPage(this);
        pageObject.navigateToWikipedia();
        pageObject.fillSearchField("jkhfgdsjkfhgkjdshfgkjdshfgkjhdfsgkjhdfsgkjhdfsgkjhdfsgkjhdfsgkjhdfsgkjhdfsgkjhdfsgkjhdfsgkjhdfsgkjhdfsgkjhdfsgkjhdfsgkjhdfsgkjhdfsgkjhdfsgkjhdfsgkjhdfsg");
        pageObject.clickSearchButton();
        pageObject.verifyErrorMessageForInvalidSearch();
        pageObject.takeScreenshot("invalid-search-error.png");
    }
}
