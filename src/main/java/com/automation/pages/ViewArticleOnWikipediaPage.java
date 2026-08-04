package com.automation.pages;

import com.automation.base.BaseTestManager;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.options.LoadState;

public class ViewArticleOnWikipediaPage {
    private final BaseTestManager testManager;
    private final Locator searchInput;
    private final Locator searchButton;
    private final Locator topSearchResult;

    public ViewArticleOnWikipediaPage(BaseTestManager testManager) {
        this.testManager = testManager;
        this.searchInput = testManager.getPage().locator("#searchInput");
        this.searchButton = testManager.getPage().locator("button[type='submit']");
        this.topSearchResult = testManager.getPage().locator("a.mw-searchSuggest-link").first();
    }

    public void navigateToWikipedia() {
        testManager.getPage().navigate("https://www.wikipedia.org/");
    }

    public void searchForTopic(String topic) {
        searchInput.fill(topic);
    }

    public void clickSearchButton() {
        searchButton.click();
    }

    public void waitForSearchResults() {
        testManager.getPage().waitForLoadState(LoadState.NETWORKIDLE);
    }

    public void clickTopSearchResult() {
        topSearchResult.click();
    }

    public void waitForArticleLoad() {
        testManager.getPage().waitForLoadState(LoadState.NETWORKIDLE);
    }

    public void waitForNoResultsMessage() {
        testManager.getPage().waitForLoadState(LoadState.NETWORKIDLE);
    }

    public void waitForErrorMessage() {
        testManager.getPage().waitForLoadState(LoadState.NETWORKIDLE);
    }

    public void takeScreenshot(String filename) {
        testManager.getPage().screenshot(new com.microsoft.playwright.Page.ScreenshotOptions().setPath(java.nio.file.Paths.get(filename)));
    }
}
