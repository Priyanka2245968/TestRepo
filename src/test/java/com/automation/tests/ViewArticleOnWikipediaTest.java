package com.automation.tests;

import com.automation.base.BaseTestManager;
import com.automation.pages.ViewArticleOnWikipediaPage;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.options.LoadState;
import org.testng.annotations.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class ViewArticleOnWikipediaTest extends BaseTestManager {

    @Test
    public void testHappyPathViewWikipediaArticle() {
        ViewArticleOnWikipediaPage pageObject = new ViewArticleOnWikipediaPage(this);
        pageObject.navigateToWikipediaHomepage();
        pageObject.searchForTopic("Photosynthesis");
        pageObject.clickSearchButton();
        pageObject.clickPhotosynthesisLink();
        assertThat(page).hasTitle("Photosynthesis - Wikipedia");
        pageObject.takeScreenshot("photosynthesis-article.png");
    }

    @Test
    public void testNegativeSearchFieldEmpty() {
        ViewArticleOnWikipediaPage pageObject = new ViewArticleOnWikipediaPage(this);
        pageObject.navigateToWikipediaHomepage();
        pageObject.clickSearchButton();
        Locator searchErrorMessage = pageObject.getSearchErrorMessage();
        assertThat(searchErrorMessage).containsText("Please enter a search term");
        pageObject.takeScreenshot("search-empty-error.png");
    }

    @Test
    public void testBoundaryLongSearchQuery() {
        ViewArticleOnWikipediaPage pageObject = new ViewArticleOnWikipediaPage(this);
        pageObject.navigateToWikipediaHomepage();
        String longQuery = "This is a very long search query that exceeds the maximum length allowed by Wikipedia's search engine. It should trigger an error or truncation behavior.";
        pageObject.searchForTopic(longQuery);
        pageObject.clickSearchButton();
        page.waitForLoadState(LoadState.NETWORKIDLE);
        // Add assertions for the expected behavior with a long search query
        pageObject.takeScreenshot("long-search-query.png");
    }
}