package com.automation.tests;

import com.automation.base.BaseTestManager;
import com.automation.pages.ViewArticlePage;
import com.microsoft.playwright.options.LoadState;
import org.testng.annotations.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class ViewArticleTest extends BaseTestManager {

    @Test
    public void testHappyPathSearchAndViewArticle() {
        ViewArticlePage pageObject = new ViewArticlePage(this);
        pageObject.navigateToWikipedia();
        pageObject.searchForArticle("HTML");
        pageObject.clickSearchButton();
        pageObject.clickArticleLink("HTML - Wikipedia");
        page.waitForLoadState(LoadState.NETWORKIDLE);
        assertThat(page).hasTitle("HTML - Wikipedia");
        pageObject.takeScreenshot("article-view.png");
    }

    @Test
    public void testNegativeBlankSearch() {
        ViewArticlePage pageObject = new ViewArticlePage(this);
        pageObject.navigateToWikipedia();
        pageObject.clickSearchButton();
        page.waitForLoadState(LoadState.NETWORKIDLE);
        assertThat(page.locator(".mw-search-results")).containsText("No results found. Please try a different query.");
        pageObject.takeScreenshot("blank-search.png");
    }

    @Test
    public void testNegativeLongSearch() {
        ViewArticlePage pageObject = new ViewArticlePage(this);
        String longText = "This is a very long string with more than 500 characters. This is a very long string with more than 500 characters. This is a very long string with more than 500 characters. This is a very long string with more than 500 characters. This is a very long string with more than 500 characters. This is a very long string with more than 500 characters. This is a very long string with more than 500 characters. This is a very long string with more than 500 characters. This is a very long string with more than 500 characters.";
        pageObject.navigateToWikipedia();
        pageObject.searchForArticle(longText);
        pageObject.clickSearchButton();
        page.waitForLoadState(LoadState.NETWORKIDLE);
        assertThat(page.locator(".mw-search-results")).containsText("An error has occurred while searching: Search request is longer than the maximum allowed length");
        pageObject.takeScreenshot("long-search.png");
    }
}
