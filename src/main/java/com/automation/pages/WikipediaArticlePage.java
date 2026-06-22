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

    public void searchForArticle(String query) {
        System.out.println("📍 Searching for '" + query + "'");
        page.locator("#searchInput").fill(query);
        page.locator("#searchButton").click();
    }

    public void openArticle(String articleTitle) {
        System.out.println("📍 Opening article: " + articleTitle);
        page.locator("//a[contains(@title, '" + articleTitle + "')]").first().click();
    }

    public boolean isArticleDisplayed(String articleTitle) {
        System.out.println("📍 Verifying article: " + articleTitle);
        return page.locator("//h1[contains(., '" + articleTitle + "')]").isVisible();
    }

    public void takeScreenshot(String filename) {
        System.out.println("📸 Taking screenshot: " + filename);
        page.screenshot(new com.microsoft.playwright.options.ScreenshotOptions().setPath(com.microsoft.playwright.impl.Playwright.getBrowserType().defaultBrowserContext().traceViewer().source().saveAs(filename)));
    }
}