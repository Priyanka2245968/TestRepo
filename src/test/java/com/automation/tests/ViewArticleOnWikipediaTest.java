package com.automation.tests;

import com.automation.base.BaseTestManager;
import com.automation.pages.ViewArticleOnWikipediaPage;
import com.microsoft.playwright.options.LoadState;
import org.testng.annotations.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class ViewArticleOnWikipediaTest extends BaseTestManager {

    @Test
    public void testHappyPathViewWikipediaArticle() {
        ViewArticleOnWikipediaPage pageObject = new ViewArticleOnWikipediaPage(this);
        pageObject.navigateToWikipedia();
        pageObject.searchForTopic("HTML element");
        pageObject.clickSearchButton();
        pageObject.waitForSearchResults();
        pageObject.clickTopSearchResult();
        pageObject.waitForArticleLoad();
        assertThat(getPage()).hasTitle("HTML element - Wikipedia");
        pageObject.takeScreenshot("wikipedia-article.png");
    }

    @Test
    public void testNegativeBlankSearchQuery() {
        ViewArticleOnWikipediaPage pageObject = new ViewArticleOnWikipediaPage(this);
        pageObject.navigateToWikipedia();
        pageObject.clickSearchButton();
        pageObject.waitForNoResultsMessage();
        assertThat(getPage()).hasTitle("Wikipedia");
        pageObject.takeScreenshot("no-results.png");
    }

    @Test
    public void testNegativeSearchQueryTooLong() {
        ViewArticleOnWikipediaPage pageObject = new ViewArticleOnWikipediaPage(this);
        pageObject.navigateToWikipedia();
        String longQuery = "A string longer than 500 characters";
        for (int i = 0; i < 10; i++) {
            longQuery += longQuery;
        }
        pageObject.searchForTopic(longQuery);
        pageObject.clickSearchButton();
        pageObject.waitForErrorMessage();
        assertThat(getPage()).hasTitle("Wikipedia");
        pageObject.takeScreenshot("query-too-long.png");
    }

    @Test
    public void testNegativeSearchQueryNoResults() {
        ViewArticleOnWikipediaPage pageObject = new ViewArticleOnWikipediaPage(this);
        pageObject.navigateToWikipedia();
        pageObject.searchForTopic("qwertyuiopasdfghjklzxcvbnm");
        pageObject.clickSearchButton();
        pageObject.waitForNoResultsMessage();
        assertThat(getPage()).hasTitle("Wikipedia");
        pageObject.takeScreenshot("no-results.png");
    }
}
