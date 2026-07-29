package com.automation.pages;

import com.microsoft.playwright.Page;
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
    
    public void enterSearchTerm(String term) {
        System.out.println("📍 Enter '" + term + "' in the search box");
        page.locator("#searchInput").fill(term);
    }
    
    public void clickSearchButton() {
        System.out.println("📍 Click the 'Search Wikipedia' button");
        page.locator("button[type='submit']").first().click();
        page.waitForTimeout(3000); // Wait for page navigation after click
    }
    
    public void clickTopSearchResult() {
        System.out.println("📍 Click the top search result link");
        page.locator("a[href=\"/wiki/Main_Page\"]").first().click();
        page.waitForTimeout(3000); // Wait for page navigation after click
    }
    
    public void takeScreenshot(String filename) {
        page.screenshot(new Page.ScreenshotOptions().setPath(java.nio.file.Paths.get(filename)));
        System.out.println("📸 Screenshot saved: " + filename);
    }
}