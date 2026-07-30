package com.automation.pages;

import com.automation.base.BaseTestManager;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;

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

    public void navigateToWikipedia() {
        page.navigate("https://www.wikipedia.org/");
    }

    public void fillSearchField(String text) {
        searchInput.fill(text);
    }

    public void clickSearchButton() {
        searchButton.click();
    }

    public void waitForSearchResults() {
        page.waitForLoadState(LoadState.NETWORKIDLE);
    }

    public void clickFirstSearchResult() {
        firstSearchResult.click();
    }

    public void waitForArticlePage() {
        page.waitForLoadState(LoadState.NETWORKIDLE);
    }

    public void waitForWikipediaHomePage() {
        page.waitForLoadState(LoadState.NETWORKIDLE);
    }

    public void waitForNoSearchResults() {
        page.waitForLoadState(LoadState.NETWORKIDLE);
    }

    public void takeScreenshot(String filename) {
        page.screenshot(new Page.ScreenshotOptions().setPath(java.nio.file.Paths.get(filename)));
    }
}