package com.automation.pages;

import com.microsoft.playwright.Page;
import com.automation.base.BaseTestManager;

public class WikipediaSearchPage {
    private Page page;
    
    public WikipediaSearchPage(BaseTestManager testManager) {
        this.page = testManager.getPage();
    }
    
    public void navigateToWikipedia() {
        System.out.println("📍 Navigate to Wikipedia homepage");
        page.navigate("https://www.wikipedia.org/");
    }
    
    public void enterSearchQuery(String query) {
        System.out.println("📍 Enter '" + query + "' in the search box");
        page.locator("#searchInput").fill(query);
    }
    
    public void clickSearchButton() {
        System.out.println("📍 Click the Search button");
        page.locator("//button[contains(normalize-space(.),\"Search\")]").first().click();
        page.waitForTimeout(3000); // wait for navigation
    }
    
    public void clickFirstSearchResult() {
        System.out.println("📍 Click the first search result link");
        page.locator("//a[contains(@title, 'HTML')]").first().click();
        page.waitForTimeout(3000); // wait for navigation
    }
    
    public void takeScreenshot(String filename) {
        page.screenshot(new Page.ScreenshotOptions().setPath(java.nio.file.Paths.get(filename)));
        System.out.println("📸 Screenshot saved: " + filename);
    }
}