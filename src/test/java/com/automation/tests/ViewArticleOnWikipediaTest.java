package com.automation.tests;

import com.automation.base.BaseTestManager;
import com.automation.pages.WikipediaArticlePage;
import com.microsoft.playwright.options.LoadState;
import org.testng.annotations.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.testng.Assert.assertTrue;

public class ViewArticleOnWikipediaTest extends BaseTestManager {

    @Test
    public void testHappyPath_ViewWikipediaArticleForASearchedTopic() {
        WikipediaArticlePage pageObject = new WikipediaArticlePage(this);
        pageObject.navigateToWikipedia();
        pageObject.searchForTerm("HTML");
        pageObject.waitForSearchResults();
        pageObject.clickHtmlLinkInSearchResults();
        pageObject.waitForArticlePageLoad();
        assertThat(page).hasTitle("HTML - Wikipedia");
        pageObject.takeScreenshot("html-article.png");
    }

    @Test
    public void testAdditionalAcceptanceCriterion_WikipediaAccessibleWithoutLogin() {
        WikipediaArticlePage pageObject = new WikipediaArticlePage(this);
        pageObject.navigateToWikipedia();
        assertTrue(pageObject.getSearchField().isVisible());
        pageObject.takeScreenshot("wikipedia-homepage.png");
    }

    @Test
    public void testNegative_BlankSearchReturnsNoResults() {
        WikipediaArticlePage pageObject = new WikipediaArticlePage(this);
        pageObject.navigateToWikipedia();
        pageObject.clickSearchButton();
        page.waitForLoadState(LoadState.NETWORKIDLE);
        assertThat(page).hasTitle("asdfghjklqwertyuiop - Search results - Wikipedia");
        assertThat(pageObject.getNoResultsMessage()).containsText("No results found. Please try a different search.");
        pageObject.takeScreenshot("no-results.png");
    }

    @Test
    public void testNegativeLongSearchQueryOver500Characters() {
        WikipediaArticlePage pageObject = new WikipediaArticlePage(this);
        pageObject.navigateToWikipedia();
        String longQuery = "a".repeat(501);
        pageObject.searchForTerm(longQuery);
        pageObject.clickSearchButton();
        page.waitForLoadState(LoadState.NETWORKIDLE);
        assertTrue(pageObject.getSearchField().inputValue().equals(longQuery));
        assertThat(page).hasTitle("Wikipedia, the free encyclopedia");
        assertThat(pageObject.getErrorMessage()).containsText("Search request is longer than the maximum allowed length");
        pageObject.takeScreenshot("long-search-query-error.png");
    }

    @Test
    public void testNegativeInvalidSearchQueryWithNoMatchingResults() {
        WikipediaArticlePage pageObject = new WikipediaArticlePage(this);
        pageObject.navigateToWikipedia();
        String invalidQuery = "asdf9876zxcv";
        pageObject.searchForTerm(invalidQuery);
        pageObject.clickSearchButton();
        page.waitForLoadState(LoadState.NETWORKIDLE);
        assertTrue(pageObject.getSearchField().inputValue().equals(invalidQuery));
        assertThat(page).hasTitle("asdf9876zxcv - Search results - Wikipedia");
        assertThat(pageObject.getNoResultsMessage()).containsText("There were no results matching the query");
        pageObject.takeScreenshot("no-results-error.png");
    }
}