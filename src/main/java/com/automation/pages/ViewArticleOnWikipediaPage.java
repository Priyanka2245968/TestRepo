package com.automation.pages;

import com.automation.base.BaseTestManager;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;

public class ViewArticleOnWikipediaPage {
    private final Page page;
    private final Locator searchField;
    private final Locator searchButton;
    private final Locator htmlLinkInSearchResults;
    private final Locator noResultsMessage;

    public ViewArticleOnWikipediaPage(BaseTestManager testManager) {
        this.page = testManager.getPage();
        this.searchField = page.locator("#searchInput");
        this.searchButton = page.locator("button[type='submit']");
        this.htmlLinkInSearchResults = page.locator("//a[contains(@href, '/wiki/HTML')]");
        this.noResultsMessage = page.locator(".mw-search-nonefound");
    }

    public void navigateToWikipedia() {
        page.navigate("https://www.wikipedia.org/");
    }

    public void searchForTerm(String term) {
        searchField.fill(term);
        searchButton.click();
    }

    public void waitForSearchResults() {
        page.waitForLoadState(LoadState.NETWORKIDLE);
    }

    public void clickHtmlLinkInSearchResults() {
        htmlLinkInSearchResults.click();
    }

    public void waitForArticlePageLoad() {
        page.waitForLoadState(LoadState.NETWORKIDLE);
    }

    public Locator getSearchField() {
        return searchField;
    }

    public Locator getNoResultsMessage() {
        return noResultsMessage;
    }

    public void takeScreenshot(String fileName) {
        page.screenshot(new Page.ScreenshotOptions().setPath(fileName));
    }
}