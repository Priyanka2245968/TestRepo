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
        System.out.println("📍 Click the search button");
        page.locator("//button[contains(normalize-space(.),\"search\")]").first().click();
    }
    
    public void clickFirstResultLink(String linkText) throws Exception {
        System.out.println("📍 Click the first link titled '" + linkText + "'");
        page.locator("//a[contains(normalize-space(.),\"" + linkText + "\")]").first().click();
    }
    
    public void expandSection(String sectionName) throws Exception {
        System.out.println("📍 Expand the '" + sectionName + "' section in the left panel");
        // expand 'History'
    }
    
    public void takeScreenshot(String filename) {
        page.screenshot(new Page.ScreenshotOptions().setPath(java.nio.file.Paths.get(filename)));
        System.out.println("📸 Screenshot saved: " + filename);
    }
}