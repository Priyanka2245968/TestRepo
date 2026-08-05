package com.automation.pages;

import com.automation.base.BaseTestManager;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.testng.Assert.assertTrue;

public class ViewArticleOnWikipediaPage {

    private final Page page;
    private final Locator searchInput;
    private final Locator searchButton;
    private final Locator firstSearchResult;

    public ViewArticleOnWikipediaPage(BaseTestManager testManager) {
        this.page = testManager.getPage();
        this.searchInput = page.locator("#searchInput");
        this.searchButton = page.locator("button[type='submit']");
        this.firstSearchResult = page.locator("a[href='/wiki/Main_Page']").first();
    }

    public void navigateToWikipediaHomepage() {
        page.navigate("https://www.wikipedia.org/");
    }

    public void searchForTerm(String term) {
        searchInput.fill(term);
    }

    public void clickSearchButton() {
        searchButton.click();
    }

    public void waitForSearchResultsPage() {
        page.waitForLoadState(LoadState.NETWORKIDLE);
    }

    public void clickFirstSearchResult() {
        firstSearchResult.click();
    }

    public void waitForArticlePage() {
        page.waitForLoadState(LoadState.NETWORKIDLE);
    }

    public void verifyHomepageContentAccessible() {
        assertTrue(searchInput.isVisible());
    }

    public void verifyNoSearchTermMessage() {
        page.waitForLoadState(LoadState.NETWORKIDLE);
        assertThat(page).hasTitle("Search - Wikipedia");
        assertTrue(page.locator("//p[contains(text(), 'Please enter a search term')]").isVisible());
    }

    public void takeScreenshot(String filename) {
        page.screenshot(new Page.ScreenshotOptions().setPath(java.nio.file.Paths.get(filename)));
    }
}