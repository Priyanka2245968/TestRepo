package com.automation.pages;

import com.automation.base.BaseTestManager;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import java.nio.file.Paths;

public class WikipediaArticlePage {
    private final Page page;
    private final Locator searchInput;
    private final Locator searchButton;
    private final Locator firstSearchResult;

    public WikipediaArticlePage(BaseTestManager testManager) {
        this.page = testManager.getPage();
        this.searchInput = page.locator("#searchInput");
        this.searchButton = page.locator("button[type='submit']");
        this.firstSearchResult = page.locator(".mw-search-results a").first();
    }

    public void navigateToWikipedia() {
        page.navigate("https://www.wikipedia.org/");
    }

    public void searchForTerm(String term) {
        searchInput.fill(term);
    }

    public void clickSearchButton() {
        searchButton.click();
    }

    public void clickFirstSearchResult() {
        firstSearchResult.click();
    }

    public void takeScreenshot(String filename) {
        page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get(filename)));
    }
}