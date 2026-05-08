package com.automation.pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.Locator;
import com.automation.base.BaseTestManager;

public class WikipediaArticleViewPage {
    private Page page;
    
    public WikipediaArticleViewPage(BaseTestManager testManager) {
        this.page = testManager.getPage();
    }
    
    public void navigateToWikipediaHomePage() throws Exception {
        System.out.println("📍 Navigate to Wikipedia home page");
        page.navigate("https://www.wikipedia.org");
    }
    
    public void enterSearchTerm(String searchTerm) throws Exception {
        System.out.println("📍 Enter search term in search box");
        page.locator("#searchInput").first().fill(searchTerm);
    }
    
    public void clickSearchButton() throws Exception {
        System.out.println("📍 Click the search button");
        page.locator("//button[contains(normalize-space(.),'Search')]").first().click();
        page.waitForTimeout(3000); // Wait for navigation after click
    }
    
    public void waitForSearchResultsPage() throws Exception {
        System.out.println("📍 Wait for search results page to load");
        page.waitForURL("**/*");
    }
    
    public void clickFirstSearchResultLink() throws Exception {
        System.out.println("📍 Click the first search result link");
        page.locator("a[href='/wiki/Category:Python_(programming_language)']").first().click();
        page.waitForTimeout(3000); // Wait for navigation after click
    }
    
    public void waitForArticlePage() throws Exception {
        System.out.println("📍 Wait for article page to load");
        page.waitForURL("**/*");
    }
    
    public void takeScreenshot(String filename) {
        page.screenshot(new Page.ScreenshotOptions().setPath(java.nio.file.Paths.get(filename)));
        System.out.println("📸 Screenshot saved: " + filename);
    }
}