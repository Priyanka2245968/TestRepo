package com.automation.pages;

import com.automation.base.BaseTestManager;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.options.LoadState;

public class ViewArticleOnWikipediaPage {
    private final BaseTestManager testManager;
    private final Locator searchInput;
    private final Locator searchButton;
    private final Locator articleLink;
    private final Locator homepageElement;
    private final Locator noResultsElement;

    public ViewArticleOnWikipediaPage(BaseTestManager testManager) {
        this.testManager = testManager;
        this.searchInput = testManager.getPage().locator("#searchInput");
        this.searchButton = testManager.getPage().locator("button[type='submit']");
        this.articleLink = testManager.getPage().locator("a[href='/wiki/HTML']").first();
        this.homepageElement = testManager.getPage().locator(".central-featured");
        this.noResultsElement = testManager.getPage().locator(".no-results-info");
    }

    public void navigateToWikipedia() {
        testManager.getPage().navigate("https://www.wikipedia.org/");
    }

    public void searchForArticle(String articleName) {
        searchInput.fill(articleName);
    }

    public void clickSearchButton() {
        searchButton.click();
    }

    public void clickArticleLink() {
        articleLink.click();
        testManager.getPage().waitForLoadState(LoadState.NETWORKIDLE);
    }

    public Locator getHomepageElement() {
        return homepageElement;
    }

    public Locator getNoResultsElement() {
        return noResultsElement;
    }

    public void viewArticle() {
        searchInput.fill("HTML");
        searchButton.click();
        articleLink.click();
        testManager.getPage().waitForLoadState(LoadState.NETWORKIDLE);
    }

    public void takeScreenshot(String filename) {
        testManager.getPage().screenshot(new com.microsoft.playwright.Page.ScreenshotOptions().setPath(java.nio.file.Paths.get(filename)));
    }

    public void waitForTimeout(int milliseconds) {
        testManager.getPage().waitForTimeout(milliseconds);
    }
}
