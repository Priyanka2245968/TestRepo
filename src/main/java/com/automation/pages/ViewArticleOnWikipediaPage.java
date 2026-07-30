package com.automation.pages;

import com.automation.base.BaseTestManager;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.options.LoadState;

public class ViewArticleOnWikipediaPage {

    private final BaseTestManager testManager;
    private final Locator searchInput;
    private final Locator searchButton;
    private final Locator htmlLink;

    public ViewArticleOnWikipediaPage(BaseTestManager testManager) {
        this.testManager = testManager;
        this.searchInput = testManager.getPage().locator("#searchInput");
        this.searchButton = testManager.getPage().locator("button[type='submit']");
        this.htmlLink = testManager.getPage().locator("a[href='/wiki/HTML']").first();
    }

    public void navigateToWikipedia() {
        testManager.getPage().navigate("https://www.wikipedia.org");
    }

    public void fillSearchField(String text) {
        System.out.println("📍 In the 'Search Wikipedia' field, enter '" + text + "'");
        searchInput.fill(text);
    }

    public void clickSearchButton() {
        System.out.println("📍 Click the 'Search' icon button");
        searchButton.click();
    }

    public void clickHTMLLinkInSearchResults() {
        System.out.println("📍 Click the 'HTML' link in the search results");
        htmlLink.click();
    }

    public void waitForLoadState(LoadState state) {
        testManager.getPage().waitForLoadState(state);
    }

    public void takeScreenshot(String filename) {
        testManager.getPage().screenshot(new com.microsoft.playwright.Page.ScreenshotOptions().setPath(java.nio.file.Paths.get(filename)));
    }
}
