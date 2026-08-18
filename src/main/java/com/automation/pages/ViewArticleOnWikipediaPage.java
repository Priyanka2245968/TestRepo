package com.automation.pages;

import com.automation.base.BaseTestManager;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.options.LoadState;

public class ViewArticleOnWikipediaPage {
    private final com.microsoft.playwright.Page page;

    private final Locator searchInput;
    private final Locator searchButton;
    private final Locator articleHeading;

    public ViewArticleOnWikipediaPage(BaseTestManager testManager) {
        this.page = testManager.getPage();

        this.searchInput = page.locator("#searchInput");
        this.searchButton = page.locator("//button[contains(normalize-space(.),'Search')]").first();
        this.articleHeading = page.locator("h1");
    }

    public void navigateToWikipedia() {
        System.out.println("📍 Navigate to https://www.wikipedia.org");
        page.navigate("https://www.wikipedia.org");
    }

    public void enterSearchTerm(String searchTerm) {
        System.out.println("📍 Enter search term: " + searchTerm);
        searchInput.fill(searchTerm);
    }

    public void clickSearchButton() {
        System.out.println("📍 Click the Search button");
        searchButton.click();
    }

    public void verifyArticlePageLoaded() {
        System.out.println("📍 Verify the article page has loaded");
        page.waitForLoadState(LoadState.NETWORKIDLE);
        page.waitForURL("**/HTML");
    }

    public void verifyArticleHeadingDisplayed() {
        System.out.println("📍 Verify the article heading is displayed");
        articleHeading.waitFor(new Locator.WaitForOptions().setTimeout(10000));
        org.testng.Assert.assertTrue(articleHeading.isVisible(), "Article heading is not visible");
    }

    public void takeScreenshot(String filename) {
        System.out.println("📷 Taking screenshot: " + filename);
        page.screenshot(new com.microsoft.playwright.Page.ScreenshotOptions().setPath(java.nio.file.Paths.get(filename)));
    }
}