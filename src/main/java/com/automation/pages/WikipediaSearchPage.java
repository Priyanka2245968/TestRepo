package com.automation.pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.Locator;
import com.automation.base.BaseTestManager;

public class WikipediaSearchPage {
    private Page page;
    
    public WikipediaSearchPage(BaseTestManager testManager) {
        this.page = testManager.getPage();
    }
    
    public void navigateToHomepage() {
        System.out.println("📍 Navigate to Wikipedia homepage");
        page.navigate("https://www.wikipedia.org/");
    }
    
    public void enterSearchText(String text) {
        System.out.println("📍 Enter '" + text + "' in the search box");
        page.locator("#searchInput").fill(text);
    }
    
    public void clickSearchButton() {
        System.out.println("📍 Click the Search button");
        page.locator("//button[contains(normalize-space(.),'Search')]").first().click();
        page.waitForTimeout(3000); // Wait for navigation after click
    }
    
    public void clickFirstSearchResult() {
        System.out.println("📍 Click the first result titled 'HTML Table'");
        page.locator("a[href='/w/index.php?title=HTML_tables&redirect=no']").first().click();
        page.waitForTimeout(3000); // Wait for navigation after click
    }
    
    public void takeScreenshot(String filename) {
        page.screenshot(new Page.ScreenshotOptions().setPath(java.nio.file.Paths.get(filename)));
        System.out.println("📸 Screenshot saved: " + filename);
    }
}