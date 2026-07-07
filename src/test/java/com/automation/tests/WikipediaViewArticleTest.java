package com.automation.tests;

import com.automation.base.BaseTestManager;
import com.automation.pages.WikipediaViewArticlePage;
import com.microsoft.playwright.options.LoadState;
import org.testng.annotations.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class WikipediaViewArticleTest extends BaseTestManager {

    @Test
    public void testNegativeWhenVeryLongTextIsProvided() {
        WikipediaViewArticlePage pageObject = new WikipediaViewArticlePage(this);
        pageObject.navigateToWikipedia();
        pageObject.enterVeryLongTextInSearchBox();
        pageObject.clickSearchButton();
        assertThat(pageObject.getErrorMessage()).contains("The search query is too long");
        pageObject.takeScreenshot("long_search_error.png");
    }

    @Test
    public void testNegativeWhenInvalidTextIsProvided() {
        WikipediaViewArticlePage pageObject = new WikipediaViewArticlePage(this);
        pageObject.navigateToWikipedia();
        pageObject.enterInvalidTextInSearchBox();
        pageObject.clickSearchButton();
        assertThat(pageObject.getNoResultsMessage()).contains("No results found");
        pageObject.takeScreenshot("invalid_search_error.png");
    }
}