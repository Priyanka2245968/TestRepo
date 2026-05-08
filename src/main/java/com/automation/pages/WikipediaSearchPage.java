package com.automation.pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.Locator;
import com.automation.base.BaseTestManager;

public class WikipediaSearchPage {
    private Page page;
    
    public WikipediaSearchPage(BaseTestManager testManager) {
        this.page = testManager.getPage();
    }
    
    public void navigateToWikipediaHomepage() throws Exception {
        System.out.println("📍 Navigate to Wikipedia homepage");
        page.navigate("https://www.wikipedia.org/");
    }
    
    public void enterSearchQuery(String query) throws Exception {
        System.out.println("📍 Enter '" + query + "' in the search box");
        page.locator("#searchInput").first().fill(query);
    }
    
    public void clickSearchButton() throws Exception {
        System.out.println("📍 Click the Search button");
        page.locator("//button[contains(normalize-space(.),'Search')]").first().click();
        page.waitForTimeout(3000); // Wait for navigation after click
    }
    
    public void clickHTMLLink() throws Exception {
        System.out.println("📍 Click the 'HTML' link in search results");
        page.locator("//button[contains(normalize-space(.),'HTML')]").first().click();
        page.waitForTimeout(3000); // Wait for navigation after click
    }
    
    public void takeScreenshot(String filename) {
        page.screenshot(new Page.ScreenshotOptions().setPath(java.nio.file.Paths.get(filename)));
        System.out.println("📸 Screenshot saved: " + filename);
    }
}