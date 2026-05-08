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
    
    public void pressEnterToSearch() {
        System.out.println("📍 Press Enter to submit the search");
        page.locator("body").first().press("Enter");
    }
    
    public void clickSearchResult(String linkText) {
        System.out.println("📍 Click on the first search result titled '" + linkText + "'");
        page.locator("a[href='/wiki/Category:Python_(programming_language)']").first().click();
        page.waitForTimeout(3000); // Wait for new page to load after navigation
    }
    
    public void takeScreenshot(String filename) {
        page.screenshot(new Page.ScreenshotOptions().setPath(java.nio.file.Paths.get(filename)));
        System.out.println("📸 Screenshot saved: " + filename);
    }
}