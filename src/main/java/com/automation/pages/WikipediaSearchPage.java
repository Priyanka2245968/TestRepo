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
        page.locator("//button[normalize-space(.)='Search']").first().click();
        page.waitForTimeout(3000); // Wait for navigation after click
    }
    
    public void clickPythonProgrammingLanguageLink() throws Exception {
        System.out.println("📍 Click the first link titled 'Python (programming language)'");
        page.locator("//a[normalize-space(.)='Python (programming language)']").first().click();
        page.waitForTimeout(3000); // Wait for navigation after click
    }
    
    public void expandTableOfContents() throws Exception {
        System.out.println("📍 Expand the 'Table of contents' section");
        // No selector found in execution log for this step
    }
    
    public void takeScreenshot(String filename) {
        page.screenshot(new Page.ScreenshotOptions().setPath(java.nio.file.Paths.get(filename)));
        System.out.println("📸 Screenshot saved: " + filename);
    }
}