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
        page.navigate("https://www.wikipedia.org");
    }
    
    public void enterSearchText(String text) throws Exception {
        System.out.println("📍 Enter '" + text + "' in the search box");
        page.locator("#searchInput").first().fill(text);
    }
    
    public void clickSearchButton() throws Exception {
        System.out.println("📍 Click the Search button next to the search field");
        page.locator("//button[contains(normalize-space(.),'Search')]").first().click();
        page.waitForTimeout(3000); // Wait for page navigation
    }
    
    public void clickHTMLLink() throws Exception {
        System.out.println("📍 Click the 'HTML' link under the 'Did you mean:' section");
        page.locator("//button[contains(normalize-space(.),'HTML')]").first().click();
        page.waitForTimeout(3000); // Wait for page navigation
    }
    
    public void takeScreenshot(String filename) throws Exception {
        System.out.println("📍 Take a screenshot of the HTML article page");
        page.screenshot(new Page.ScreenshotOptions().setPath(java.nio.file.Paths.get(filename)));
        System.out.println("📸 Screenshot saved: " + filename);
    }
}