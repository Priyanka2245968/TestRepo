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

    public void searchWikipedia(String query) {
        System.out.println("📍 Searching Wikipedia for '" + query + "'");
        page.locator("input[name='search']").fill(query);
        page.locator("button[type='submit']").click();
    }

    public void clickSearchResult(String title) {
        System.out.println("📍 Clicking search result '" + title + "'");
        page.locator("a:has-text('" + title + "')").first().click();
    }

    public void verifyArticleTitle(String title) {
        System.out.println("📍 Verifying article title '" + title + "'");
        page.locator("h1:has-text('" + title + "')").isVisible();
    }

    public void takeScreenshot(String filename) {
        System.out.println("📸 Taking screenshot: " + filename);
        page.screenshot(new com.microsoft.playwright.options.ScreenshotOptions().setPath(com.microsoft.playwright.utils.Utils.map("screenshots/" + filename + ".png")));
    }
}