package com.automation.pages;

import com.microsoft.playwright.Page;
import com.automation.base.BaseTestManager;

public class WikipediaArticleViewPage {
    private Page page;

    public WikipediaArticleViewPage(BaseTestManager testManager) {
        this.page = testManager.getPage();
    }

    public void navigateToWikipediaHomepage() {
        System.out.println("📍 Navigate to Wikipedia homepage");
        page.navigate("https://www.wikipedia.org/");
    }

    public void enterSearchText(String text) {
        System.out.println("📍 Enter '" + text + "' in the search box");
        page.locator("#searchInput").first().fill(text);
    }

    public void clickSearchButton() {
        System.out.println("📍 Click the 'Search' button (magnifying glass icon)");
        page.locator("//button[contains(normalize-space(.),'Search')]").first().click();
        page.waitForTimeout(3000); // Wait for page navigation
    }

    public void clickFirstSearchResult() {
        System.out.println("📍 Click the first search result link titled 'HTML Tutorial'");
        page.locator("a[href='/wiki/HTML_Tutorial']").first().click();
        page.waitForTimeout(3000); // Wait for page navigation
    }

    public void takeScreenshot(String filename) {
        System.out.println("📍 Take a screenshot of the 'HTML Tutorial' article page");
        page.screenshot(new Page.ScreenshotOptions().setPath(java.nio.file.Paths.get(filename)));
        System.out.println("📸 Screenshot saved: " + filename);
    }
}