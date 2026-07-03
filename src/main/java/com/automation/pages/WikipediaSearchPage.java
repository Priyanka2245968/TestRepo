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
    
    public void enterSearchQuery(String query) {
        System.out.println("📍 Enter '" + query + "' in the search box");
        page.locator("#searchInput").fill(query);
    }
    
    public void clickSearchButton() {
        System.out.println("📍 Click the 'Search Wikipedia' button");
        page.locator("button[type='submit']").first().click();
        page.waitForTimeout(3000); // wait for navigation
    }
    
    public void clickPythonProgrammingLanguageLink() {
        System.out.println("📍 Click the link for the 'Python (programming language)' article");
        page.locator("a[href='/wiki/Python_(programming_language)']").first().click();
        page.waitForTimeout(3000); // wait for navigation
    }
    
    public void takeScreenshot(String filename) {
        System.out.println("📍 Take a screenshot of the 'Python (programming language)' article page");
        page.screenshot(new Page.ScreenshotOptions().setPath(java.nio.file.Paths.get(filename)));
        System.out.println("📸 Screenshot saved: " + filename);
    }
}