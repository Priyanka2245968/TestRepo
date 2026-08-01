package com.automation.pages;

import com.automation.base.BaseTestManager;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;
import com.microsoft.playwright.options.WaitForSelectorState;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.testng.Assert.assertTrue;

public class ViewArticleOnWikipediaPage {
    private final Page page;
    private final Locator searchInput;
    private final Locator searchButton;
    private final Locator searchBar;

    public ViewArticleOnWikipediaPage(BaseTestManager testManager) {
        this.page = testManager.getPage();
        this.searchInput = page.locator("#searchInput");
        this.searchButton = page.locator("button[type='submit']");
        this.searchBar = page.locator("#ooui-php-1");
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
        page.locator("a.mw-search-results-object-uri").first().click();
        page.waitForLoadState(LoadState.NETWORKIDLE);
    }

    public void verifyArticlePageLoaded() {
        Locator articleTitle = page.locator("#firstHeading");
        articleTitle.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
        assertTrue(articleTitle.isVisible());
    }

    public void verifyHomePageLoadedWithoutLogin() {
        page.waitForLoadState(LoadState.NETWORKIDLE);
        assertThat(searchBar).isVisible();
    }

    public void clickSearchWithoutText() {
        searchButton.click();
    }

    public void verifyNoResultsMessage() {
        page.waitForLoadState(LoadState.NETWORKIDLE);
        Locator noResultsMessage = page.locator("p.mw-search-nonefound");
        noResultsMessage.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
        assertTrue(noResultsMessage.isVisible());
    }

    public void fillSearchField(String text) {
        searchInput.fill(text);
    }

    public void clickSearchButton() {
        searchButton.click();
    }

    public void verifyErrorMessageDisplayed() {
        Locator errorMessage = page.locator(".mw-searchErrorBox");
        errorMessage.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
        assertTrue(errorMessage.isVisible());
    }

    public void verifyNoResultsMessageDisplayed() {
        verifyNoResultsMessage();
    }

    public void takeScreenshot(String filename) {
        page.screenshot(new Page.ScreenshotOptions().setPath(java.nio.file.Paths.get(filename)));
    }
}