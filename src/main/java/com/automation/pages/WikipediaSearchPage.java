package com.automation.pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.Locator;
import com.automation.base.BaseTestManager;

public class WikipediaSearchPage {
    private Page page;
    
    public WikipediaSearchPage(BaseTestManager testManager) {
        this.page = testManager.getPage();
    }
    
    public void navigateToWikipediaHomePage() throws Exception {
        System.out.println("📍 Navigate to Wikipedia home page");
        page.navigate("https://www.wikipedia.org/");
    }
    
    public void enterSearchTerm(String term) throws Exception {
        System.out.println("📍 Enter '" + term + "' in the search box");
        page.locator("#searchInput").first().fill(term);
    }
    
    public void clickSearchButton() throws Exception {
        System.out.println("📍 Click the Search button");
        page.locator("//button[contains(normalize-space(.),'Search')]").first().click();
        page.waitForTimeout(3000); // Wait for page navigation
    }
    
    public void clickSearchResult(String result) throws Exception {
        System.out.println("📍 Click the '" + result + "' link in search results");
        page.locator("//a[contains(normalize-space(.),'"+result+"')]").first().click();
        page.waitForTimeout(3000); // Wait for page navigation
    }
    
    public void takeScreenshot(String filename) {
        page.screenshot(new Page.ScreenshotOptions().setPath(java.nio.file.Paths.get(filename)));
        System.out.println("📸 Screenshot saved: " + filename);
    }
}