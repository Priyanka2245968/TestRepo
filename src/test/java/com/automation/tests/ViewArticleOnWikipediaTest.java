package com.automation.tests;

import com.automation.base.BaseTestManager;
import com.automation.pages.WikipediaArticlePage;
import com.microsoft.playwright.options.LoadState;
import org.testng.annotations.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class ViewArticleOnWikipediaTest extends BaseTestManager {

    @Test
    public void testHappyPathSearchAndViewArticle() {
        WikipediaArticlePage pageObject = new WikipediaArticlePage(this);
        pageObject.navigateToWikipedia();
        pageObject.searchForTerm("HTML");
        pageObject.waitForSearchResults();
        pageObject.clickSearchResult("HTML");
        pageObject.waitForArticleLoad();
        assertThat(page).hasTitle("HTML - Wikipedia");
        pageObject.takeScreenshot("html-article.png");
    }

    @Test
    public void testArticleContentReadable() {
        WikipediaArticlePage pageObject = new WikipediaArticlePage(this);
        pageObject.navigateToWikipedia();
        pageObject.searchForTerm("HTML");
        pageObject.waitForSearchResults();
        pageObject.clickSearchResult("HTML");
        pageObject.waitForArticleLoad();
        assertThat(pageObject.getArticleContent()).isVisible();
        pageObject.takeScreenshot("html-article-content.png");
    }

    @Test
    public void testNoTextInSearchBar() {
        WikipediaArticlePage pageObject = new WikipediaArticlePage(this);
        pageObject.navigateToWikipedia();
        pageObject.clickSearchButton();
        pageObject.waitForSearchResults();
        assertThat(pageObject.getSearchResultsContainer()).isHidden();
        pageObject.takeScreenshot("no-search-results.png");
    }

    @Test
    public void testNegativeSearchTextExceeds500Characters() {
        WikipediaArticlePage pageObject = new WikipediaArticlePage(this);
        pageObject.navigateToWikipedia();
        pageObject.enterSearchText("A text string with more than 500 characters. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.");
        pageObject.clickSearchButton();
        page.waitForLoadState(LoadState.NETWORKIDLE);
        assertThat(pageObject.getErrorMessage()).containsText("An error has occurred while searching: Search request is longer than the maximum allowed length");
        pageObject.takeScreenshot("negative-search-text-exceeds-500-chars.png");
    }

    @Test
    public void testNegativeSearchForInvalidText() {
        WikipediaArticlePage pageObject = new WikipediaArticlePage(this);
        pageObject.navigateToWikipedia();
        pageObject.enterSearchText("asdf@#$%^&*()");
        pageObject.clickSearchButton();
        page.waitForLoadState(LoadState.NETWORKIDLE);
        assertThat(pageObject.getErrorMessage()).containsText("There were no results matching the query");
        pageObject.takeScreenshot("negative-search-invalid-text.png");
    }
}