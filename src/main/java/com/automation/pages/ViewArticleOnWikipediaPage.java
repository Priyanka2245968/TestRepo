package com.automation.pages;

import com.automation.base.BaseTestManager;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class ViewArticleOnWikipediaPage {
    private final Page page;
    private final Locator searchField;
    private final Locator searchButton;
    private final Locator htmlLink;
    private final Locator errorMessage;
    private final Locator noResultsMessage;

    public ViewArticleOnWikipediaPage(BaseTestManager testManager) {
        this.page = testManager.getPage();
        this.searchField = page.locator("#searchInput, #ooui-php-1");
        this.searchButton = page.locator("button[type='submit']");
        this.htmlLink = page.locator("a[href='/wiki/Main_Page']");
        this.errorMessage = page.locator(".mw-message-box");
        this.noResultsMessage = page.locator(".mw-search-nonefound");
    }

    public void navigateToWikipedia() {
        page.navigate("https://www.wikipedia.org/");
    }

    public void enterSearchQuery(String query) {
        searchField.fill(query);
    }

    public void clickSearchButton() {
        searchButton.click();
    }

    public Locator getSearchField() {
        return searchField;
    }

    public Locator getErrorMessage() {
        return errorMessage;
    }

    public Locator getNoResultsMessage() {
        return noResultsMessage;
    }

    public void takeScreenshot(String filename) {
        page.screenshot(new Page.ScreenshotOptions().setPath(java.nio.file.Paths.get(filename)));
    }
}