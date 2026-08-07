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
        page.locator("//button[contains(normalize-space(.),\"Search\")]").first().click();
    }
    
    public void clickPythonProgrammingLanguageLink() throws Exception {
        System.out.println("📍 Click the first link titled 'Python (programming language)'");
        page.locator("//a[contains(normalize-space(.),\"Python (programming language)\")]").first().click();
    }
    
    public void expandTableOfContents() throws Exception {
        System.out.println("📍 Expand the 'Table of contents' section");
        // No selector provided in execution log, skipping this step
    }
    
    public void takeScreenshot(String filename) throws Exception {
        page.screenshot(new Page.ScreenshotOptions().setPath(java.nio.file.Paths.get(filename)));
        System.out.println("📸 Screenshot saved: " + filename);
    }
}