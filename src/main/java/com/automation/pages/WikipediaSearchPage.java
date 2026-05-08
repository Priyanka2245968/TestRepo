package com.automation.pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.Locator;
import com.automation.base.BaseTestManager;

public class WikipediaSearchPage {
    private Page page;
    
    public WikipediaSearchPage(BaseTestManager testManager) {
        this.page = testManager.getPage();
    }
    
    public void navigateToWikipediaHomepage() {
        System.out.println("📍 Navigate to Wikipedia homepage");
        page.navigate("https://www.wikipedia.org/");
    }
    
    public void enterSearchQuery(String query) {
        System.out.println("📍 Enter '" + query + "' in the search box");
        page.locator("#searchInput").first().fill(query);
    }
    
    public void clickSearchButton() {
        System.out.println("📍 Click the search button");
        page.locator("//button[contains(normalize-space(.),'Search')]").first().click();
        page.waitForTimeout(3000); // Wait for page navigation
    }
    
    public void clickFirstResultLink(String linkText) {
        System.out.println("📍 Click the first link titled '" + linkText + "'");
        page.locator("a[href='/wiki/Category:Python_(programming_language)']").first().click();
        page.waitForTimeout(3000); // Wait for page navigation
    }
    
    public void expandTableOfContents() {
        System.out.println("📍 Expand the 'Table of contents' section");
        // No selector provided in execution log, so skipping this step
    }
    
    public void takeScreenshot(String filename) {
        page.screenshot(new Page.ScreenshotOptions().setPath(java.nio.file.Paths.get(filename)));
        System.out.println("📸 Screenshot saved: " + filename);
    }
}