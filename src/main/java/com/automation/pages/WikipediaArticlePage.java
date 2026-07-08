package com.automation.pages;

import com.automation.base.BaseTestManager;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;

public class WikipediaArticlePage {
    private final Page page;
    private final Locator searchField;
    private final Locator searchButton;
    private final Locator htmlLinkInSearchResults;
    private final Locator noResultsMessage;
    private final Locator errorMessage;

    public WikipediaArticlePage(BaseTestManager testManager) {
        this.page = testManager.getPage();
        this.searchField = page.locator("#searchInput, #ooui-php-1");
        this.searchButton = page.locator("button[type='submit']");
        this.htmlLinkInSearchResults = page.locator("a[href='/wiki/HTML']");
        this.noResultsMessage = page.locator(".mw-search-nonefound");
        this.errorMessage = page.locator(".mw-message-box");
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

    public Locator getErrorMessage() {
        return errorMessage;
    }

    public void takeScreenshot(String filename) {
        page.screenshot(new Page.ScreenshotOptions().setPath(java.nio.file.Paths.get(filename)));
    }
}