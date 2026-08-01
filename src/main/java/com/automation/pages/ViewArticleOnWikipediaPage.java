package com.automation.pages;

import com.automation.base.BaseTestManager;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;
import com.microsoft.playwright.options.WaitForSelectorState;
import com.microsoft.playwright.PlaywrightException;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.hamcrest.Matchers.containsString;

public class ViewArticleOnWikipediaPage {
    private final Page page;
    private final Locator searchInput;
    private final Locator searchButton;
    private final Locator searchBar;

    public ViewArticleOnWikipediaPage(BaseTestManager testManager) {
        this.page = testManager.getPage();
        this.searchInput = page.locator("input[name='search']");
        this.searchButton = page.locator("button[type='submit']");
        this.searchBar = page.locator(".search-box");
    }

    public void navigateToWikipedia() {
        page.navigate("https://www.wikipedia.org/");
    }

    public void searchForArticle(String searchTerm) {
        System.out.println("📍 Searching for article: " + searchTerm);
        searchInput.fill(searchTerm);
        searchButton.click();
    }

    public void verifySearchResultsPage() {
        page.waitForLoadState(LoadState.NETWORKIDLE);
        assertThat(page).hasTitle(containsString("Search"));
    }

    public void clickTopSearchResult() {
        page.locator(".mw-search-results li:first-child a").click();
        assertThat(page).hasURL(containsString("/wiki/"));
    }

    public void verifyArticlePageLoaded() {
        page.waitForLoadState(LoadState.NETWORKIDLE);
        assertThat(page).hasTitle(not(containsString("Search")));
    }

    public void verifyHomePageLoadedWithoutLogin() {
        page.waitForLoadState(LoadState.NETWORKIDLE);
        assertThat(page).hasURL("https://www.wikipedia.org/");
    }

    public void verifyNoResultsMessage() {
        assertThat(page.locator(".mw-search-nonefound")).isVisible();
    }

    public void clickSearchWithoutText() {
        searchButton.click();
    }

    public void takeScreenshot(String fileName) {
        page.screenshot(new Page.ScreenshotOptions().setPath(fileName));
    }
}