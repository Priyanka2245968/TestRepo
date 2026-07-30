package com.automation.pages;

import com.automation.base.BaseTestManager;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;

public class WikipediaArticlePage {
    private final Page page;
    private final Locator searchInput;
    private final Locator searchButton;
    private final Locator htmlLink;

    public WikipediaArticlePage(BaseTestManager testManager) {
        this.page = testManager.getPage();
        this.searchInput = page.locator("#searchInput");
        this.searchButton = page.locator("button[type='submit']");
        this.htmlLink = page.locator("a[href='/wiki/HTML']").first();
    }

    public void navigateToWikipedia() {
        page.navigate("https://www.wikipedia.org");
    }

    public void searchForTerm(String term) {
        System.out.println("📍 Entering search term: " + term);
        searchInput.fill(term);
    }

    public void clickSearchButton() {
        System.out.println("📍 Clicking 'Search' button");
        searchButton.click();
        page.waitForLoadState(LoadState.NETWORKIDLE);
    }

    public void clickHtmlLink() {
        System.out.println("📍 Clicking 'HTML' link in search results");
        htmlLink.click();
        page.waitForLoadState(LoadState.NETWORKIDLE);
    }

    public void takeScreenshot(String filename) {
        page.screenshot(new Page.ScreenshotOptions().setPath(java.nio.file.Paths.get("./screenshots/" + filename)));
    }
}