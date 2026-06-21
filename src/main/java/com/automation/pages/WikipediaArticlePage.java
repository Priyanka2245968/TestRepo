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

    public void verifyArticlePageLoaded(String expectedTitle) {
        System.out.println("📍 Verifying Wikipedia article page is loaded");
        String actualTitle = page.locator("head > title").first().textContent();
        if (!actualTitle.equals(expectedTitle)) {
            throw new RuntimeException("Expected title: " + expectedTitle + ", but got: " + actualTitle);
        }
    }

    public void takeScreenshot(String filename) {
        page.screenshot(new com.microsoft.playwright.options.ScreenshotOptions().setPath(com.microsoft.playwright.impl.Playwright.getBrowserType().defaultBrowserContext().tracing().startChunk().path().resolve(filename + ".png").toString()));
    }
}