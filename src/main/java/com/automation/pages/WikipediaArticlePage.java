package com.automation.pages;

import com.automation.base.BaseTestManager;
import com.microsoft.playwright.Page;
import static org.testng.Assert.assertTrue;

public class WikipediaArticlePage {
    private final BaseTestManager testManager;
    private final Page page;

    public WikipediaArticlePage(BaseTestManager testManager) {
        this.testManager = testManager;
        this.page = testManager.getPage();
    }

    public void navigateToWikipedia(String url) {
        System.out.println("\ud83d\udccd Navigating to " + url);
        page.navigate(url);
    }

    public void searchWikipedia(String searchTerm) {
        System.out.println("\ud83d\udccd Searching Wikipedia for '" + searchTerm + "'");
        page.locator("input[name='search']").fill(searchTerm);
        page.locator("button[type='submit']").click();
    }

    public void clickSearchResult(String resultLink) {
        System.out.println("\ud83d\udccd Clicking search result '" + resultLink + "'");
        page.locator("a:has-text('" + resultLink + "')").first().click();
    }

    public void verifyErrorMessage(String expectedMessage) {
        System.out.println("\ud83d\udccd Verifying error message");
        assertTrue(page.locator(".mw-message-box").isVisible());
        assertTrue(page.locator(".mw-message-box").textContent().contains(expectedMessage));
    }

    public void takeScreenshot(String filename) {
        System.out.println("\ud83d\udcf8 Taking screenshot: " + filename);
        page.screenshot(new com.microsoft.playwright.Page.ScreenshotOptions().setPath(filename));
    }
}