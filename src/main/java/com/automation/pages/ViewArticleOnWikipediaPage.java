package com.automation.pages;

import com.automation.base.BaseTestManager;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;

import java.nio.file.Paths;

public class ViewArticleOnWikipediaPage {
    private final Page page;
    private final Locator searchInput;
    private final Locator searchButton;
    private final Locator searchResultsContainer;
    private final Locator noResultsMessage;
    private final Locator searchErrorMessage;

    public ViewArticleOnWikipediaPage(BaseTestManager testManager) {
        this.page = testManager.getPage();
        this.searchInput = page.locator("#searchInput");
        this.searchButton = page.locator("button[type='submit']");
        this.searchResultsContainer = page.locator(".mw-search-results");
        this.noResultsMessage = page.locator(".mw-search-nonefound");
        this.searchErrorMessage = page.locator(".mw-message-box");
    }

    public void navigateToWikipediaHomepage() {
        page.navigate("https://www.wikipedia.org");
        page.waitForLoadState(LoadState.NETWORKIDLE);
    }

    public void searchForTerm(String term) {
        searchInput.fill(term);
        searchButton.click();
        page.waitForLoadState(LoadState.NETWORKIDLE);
    }

    public void waitForSearchResults() {
        searchResultsContainer.waitFor(new Locator.WaitForOptions().setTimeout(10000));
    }

    public void waitForNoResultsMessage() {
        noResultsMessage.waitFor(new Locator.WaitForOptions().setTimeout(10000));
    }

    public void waitForSearchErrorMessage() {
        searchErrorMessage.waitFor(new Locator.WaitForOptions().setTimeout(10000));
    }

    public Locator getSearchResultsContainer() {
        return searchResultsContainer;
    }

    public Locator getNoResultsMessage() {
        return noResultsMessage;
    }

    public Locator getSearchErrorMessage() {
        return searchErrorMessage;
    }

    public void takeScreenshot(String fileName) {
        page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get(fileName)));
    }
}