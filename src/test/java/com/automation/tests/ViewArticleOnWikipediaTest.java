package com.automation.tests;

import com.automation.base.BaseTestManager;
import com.automation.pages.ViewArticleOnWikipediaPage;
import com.microsoft.playwright.options.LoadState;
import org.testng.annotations.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class ViewArticleOnWikipediaTest extends BaseTestManager {

    @Test
    public void testSearchForTopicAndViewArticlePage() {
        ViewArticleOnWikipediaPage pageObject = new ViewArticleOnWikipediaPage(this);
        pageObject.navigateToWikipediaHomepage();
        pageObject.searchForTerm("Python Programming Language");
        pageObject.waitForSearchResults();
        assertThat(pageObject.getSearchResultsContainer()).isVisible();
        pageObject.takeScreenshot("search_results.png");
    }

    @Test
    public void testEmptySearchTerm() {
        ViewArticleOnWikipediaPage pageObject = new ViewArticleOnWikipediaPage(this);
        pageObject.navigateToWikipediaHomepage();
        pageObject.searchForTerm("");
        pageObject.waitForNoResultsMessage();
        assertThat(pageObject.getNoResultsMessage()).isVisible();
        pageObject.takeScreenshot("no_results.png");
    }

    @Test
    public void testLongSearchTerm() {
        ViewArticleOnWikipediaPage pageObject = new ViewArticleOnWikipediaPage(this);
        pageObject.navigateToWikipediaHomepage();
        String longTerm = "A very long search term with more than 500 characters exceeding the maximum allowed length for search terms on Wikipedia";
        pageObject.searchForTerm(longTerm);
        pageObject.waitForSearchErrorMessage();
        assertThat(pageObject.getSearchErrorMessage()).isVisible();
        pageObject.takeScreenshot("search_error.png");
    }
}
