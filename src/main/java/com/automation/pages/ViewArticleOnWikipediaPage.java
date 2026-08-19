package com.automation.pages;

import com.automation.base.BaseTestManager;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;
import java.nio.file.Paths;

public class ViewArticleOnWikipediaPage {
    private final Page page;

    public ViewArticleOnWikipediaPage(BaseTestManager testManager) {
        this.page = testManager.getPage();
        this.searchInput = page.locator("#searchInput");
        this.searchButton = page.locator("//button[contains(normalize-space(.),\"Search\")]").first();
    }

    private Locator searchInput;
    private Locator searchButton;

    public void navigateToWikipedia() {
        System.out.println("📍 Navigate to https://www.wikipedia.org");
        page.navigate("https://www.wikipedia.org");
    }

    public void enterSearchTerm(String term) {
        System.out.println("📍 Enter search term: " + term);
        searchInput.fill(term);
    }

    public void clickSearchButton() {
        System.out.println("📍 Click search button");
        searchButton.click();
    }

    public void verifyArticlePageLoaded() {
        System.out.println("📍 Verify article page loaded");
        page.waitForLoadState(LoadState.NETWORKIDLE);
        String currentUrl = page.url();
        assert currentUrl.contains("/wiki/") : "Article page did not load successfully";
    }

    public void verifyArticleContentVisible() {
        System.out.println("📍 Verify article content is visible");
        Locator articleContent = page.locator("#mw-content-text");
        assert articleContent.isVisible() : "Article content is not visible";
    }

    public void takeScreenshot(String filename) {
        System.out.println("📷 Taking screenshot: " + filename);
        page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get(filename)));
    }
}