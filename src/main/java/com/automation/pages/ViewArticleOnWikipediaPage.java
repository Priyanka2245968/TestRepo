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
        this.firstSearchResult = page.locator("//a[contains(@href, '/wiki/')][1]");
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

    public void takeScreenshot(String filename) {
        page.screenshot(new com.microsoft.playwright.options.ScreenshotOptions().setPath(filename));
    }

    public void verifyHomepageContentAccessible() {
        assertThat(page.locator("//a[contains(@href, '/wiki/Main_Page')]")).isVisible();
        assertThat(page.locator("//a[contains(@href, '/wiki/Contents')]")).isVisible();
        assertThat(page.locator("//a[contains(@href, '/wiki/Featured_content')]")).isVisible();
    }
}