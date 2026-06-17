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
    
    public void enterSearchText(String text) throws Exception {
        System.out.println("📍 Enter '" + text + "' in the search box");
        page.locator("#searchInput").first().fill(text);
    }
    
    public void clickSearchButton() throws Exception {
        System.out.println("📍 Click the Search button");
        page.locator("//button[contains(normalize-space(.),'Search')]").first().click();
        page.waitForTimeout(3000); // Wait for navigation after click
    }
    
    public void waitForSearchResults() throws Exception {
        System.out.println("📍 Wait for search results page to load");
        page.waitForTimeout(2000); // Wait for page load
    }
    
    public void clickSearchResult(String resultText) throws Exception {
        System.out.println("📍 Click on the first '" + resultText + "' search result");
        page.locator("a[href=\"/wiki/Main_Page\"]").first().click();
        page.waitForTimeout(3000); // Wait for navigation after click
    }
    
    public void waitForArticlePage() throws Exception {
        System.out.println("📍 Wait for 'Solar System' article page to load");
        page.waitForTimeout(2000); // Wait for page load
    }
    
    public void takeScreenshot(String filename) {
        page.screenshot(new Page.ScreenshotOptions().setPath(java.nio.file.Paths.get(filename)));
        System.out.println("📸 Screenshot saved: " + filename);
    }
}