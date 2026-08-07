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
        page.locator("button:has-text('search'), input[type='submit'][value*='search']").first().click();
    }
    
    public void clickPythonProgrammingLanguageLink() throws Exception {
        System.out.println("📍 Click the first 'Python (programming language)' link in search results");
        page.locator("a:has-text('Python (programming language)')").first().click();
    }
    
    public void waitForPageLoad() throws Exception {
        System.out.println("📍 Wait for article page to load");
        page.waitForTimeout(2000);
    }
    
    public void expandTableOfContents() throws Exception {
        System.out.println("📍 Expand the Table of Contents section");
        // No reliable selector found for expanding Table of Contents
    }
    
    public void takeScreenshot(String filename) throws Exception {
        page.screenshot(new Page.ScreenshotOptions().setPath(java.nio.file.Paths.get(filename)));
        System.out.println("📸 Screenshot saved: " + filename);
    }
}