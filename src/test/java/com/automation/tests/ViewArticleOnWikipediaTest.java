package com.automation.tests;

import com.automation.base.BaseTestManager;
import com.automation.pages.WikipediaArticlePage;
import com.microsoft.playwright.options.LoadState;
import org.testng.annotations.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.testng.Assert.assertTrue;

public class ViewArticleOnWikipediaTest extends BaseTestManager {

    @Test
    public void testHappyPathSuccessfulWikipediaArticleSearchAndView() {
        WikipediaArticlePage pageObject = new WikipediaArticlePage(this);
        pageObject.navigateToWikipedia();
        pageObject.searchForArticle("HTML");
        pageObject.clickSearchButton();
        pageObject.waitForSearchResults();
        pageObject.clickArticleLink("HTML");
        assertThat(page).hasTitle("HTML - Wikipedia");
        pageObject.takeScreenshot("html-article.png");
    }

    @Test
    public void testArticleContentIsClearAndSuitableForLearning() {
        WikipediaArticlePage pageObject = new WikipediaArticlePage(this);
        pageObject.navigateToWikipedia();
        pageObject.searchForArticle("Python (programming language)");
        pageObject.clickSearchButton();
        pageObject.waitForSearchResults();
        pageObject.clickArticleLink("Python (programming language)");
        assertThat(page).hasTitle("Python (programming language) - Wikipedia");
        pageObject.scrollThroughArticleContent();
        pageObject.takeScreenshot("python-article.png");
    }

    @Test
    public void testNegativeNoSearchInputReturnsBlankPage() {
        WikipediaArticlePage pageObject = new WikipediaArticlePage(this);
        pageObject.navigateToWikipedia();
        pageObject.clickSearchButton();
        page.waitForLoadState(LoadState.NETWORKIDLE);
        assertThat(page).hasTitle("Search results - Wikipedia");
        assertThat(page.locator(".mw-search-results")).isVisible();
        pageObject.takeScreenshot("blank-search.png");
    }

    @Test
    public void testNegativeLongSearchInputExceeding500CharactersShowsAnError() {
        ViewArticleOnWikipediaPage pageObject = new ViewArticleOnWikipediaPage(this);
        pageObject.navigateToWikipedia();
        pageObject.enterLongSearchInput();
        pageObject.clickSearchButton();
        assertThat(page).hasTitle("A very long search input exceeding 500 characters... - Search results - Wikipedia");
        String errorMessage = pageObject.getErrorMessage();
        assertTrue(errorMessage.contains("An error has occurred while searching: Search request is longer than the maximum allowed length"));
        pageObject.takeScreenshot("long_search_error.png");
    }

    @Test
    public void testNegativeInvalidSearchInputShowsNoResultsMessage() {
        ViewArticleOnWikipediaPage pageObject = new ViewArticleOnWikipediaPage(this);
        pageObject.navigateToWikipedia();
        pageObject.enterInvalidSearchInput();
        pageObject.clickSearchButton();
        page.waitForLoadState(LoadState.NETWORKIDLE);
        String noResultsMessage = pageObject.getNoResultsMessage();
        assertTrue(noResultsMessage.contains("There were no results matching the query"));
        pageObject.takeScreenshot("no_results.png");
    }
}