package com.automation.pages;

import com.automation.base.BaseTestManager;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class ViewArticleOnWikipediaPage {
    private final Page page;
    private final Locator searchInput;
    private final Locator searchButton;
    private final Locator firstSearchResultLink;
    private final Locator errorMessage;
    private final Locator noResultsMessage;
    private final Locator searchResultLinks;

    public ViewArticleOnWikipediaPage(BaseTestManager testManager) {
        this.page = testManager.getPage();
        this.searchInput = page.locator("#searchInput");
        this.searchButton = page.locator("button[type='submit']");
        this.firstSearchResultLink = page.locator("a[href='/wiki/Main_Page']");
        this.errorMessage = page.locator(".mw-message-box");
        this.noResultsMessage = page.locator(".mw-search-nonefound");
        this.searchResultLinks = page.locator(".mw-search-results a");
    }

    public void navigateToWikipediaHomepage() {
        page.navigate("https://www.wikipedia.org/");
    }

    public void enterTextInSearchField(String text) {
        searchInput.fill(text);
    }

    public void clickSearchButton() {
        searchButton.click();
    }

    public String getErrorMessage() {
        return errorMessage.textContent();
    }

    public String getNoResultsMessage() {
        return noResultsMessage.textContent();
    }

    public Locator getSearchResultLinks() {
        return searchResultLinks;
    }

    public void takeScreenshot(String filename) {
        page.screenshot(new Page.ScreenshotOptions().setPath(java.nio.file.Paths.get(filename)));
    }
}
