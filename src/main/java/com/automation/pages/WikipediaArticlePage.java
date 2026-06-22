package com.automation.pages;

import com.automation.base.BaseTestManager;
import com.microsoft.playwright.Page;

public class WikipediaArticlePage {
    private final BaseTestManager testManager;
    private final Page page;

    public WikipediaArticlePage(BaseTestManager testManager) {
        this.testManager = testManager;
        this.page = testManager.getPage();
    }

    public void navigateToWikipedia(String url) {
        System.out.println("📍 Navigating to " + url);
        page.navigate(url);
    }

    public void searchWikipedia(String searchTerm) {
        System.out.println("📍 Searching Wikipedia for '" + searchTerm + "'");
        page.locator("input[name='search']").fill(searchTerm);
        page.locator("button[type='submit']").click();
    }

    public void clickSearchResult(String resultLink) {
        System.out.println("📍 Clicking search result '" + resultLink + "'");
        page.locator("a:has-text('" + resultLink + "')").first().click();
    }

    public void verifyErrorMessage(String expectedMessage) {
        System.out.println("📍 Verifying error message");
        page.locator(".mw-message-box").isVisible();
        page.locator(".mw-message-box").textContent().contains(expectedMessage);
    }

    public void takeScreenshot(String filename) {
        System.out.println("📸 Taking screenshot: " + filename);
        page.screenshot(new com.microsoft.playwright.options.ScreenshotOptions().setPath(com.microsoft.playwright.utils.Utils.map("path", filename)));
    }
}