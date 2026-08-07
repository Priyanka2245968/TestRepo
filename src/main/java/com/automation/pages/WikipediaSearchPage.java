package com.automation.pages;

import com.microsoft.playwright.Page;
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
    
    public void pressEnterToSearch() throws Exception {
        System.out.println("📍 Press Enter to submit the search");
        page.keyboard().press("Enter");
    }
    
    public void clickSearchResult(String resultTitle) throws Exception {
        System.out.println("📍 Click on the first search result titled '" + resultTitle + "'");
        page.locator("#vector-main-menu-dropdown-checkbox").first().click();
    }
    
    public void takeScreenshot(String filename) {
        page.screenshot(new Page.ScreenshotOptions().setPath(java.nio.file.Paths.get(filename)));
        System.out.println("📸 Screenshot saved: " + filename);
    }
}