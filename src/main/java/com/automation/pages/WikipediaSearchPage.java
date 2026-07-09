package com.automation.pages;

import com.microsoft.playwright.Page;
import com.automation.base.BaseTestManager;

public class WikipediaSearchPage {
    private Page page;
    
    public WikipediaSearchPage(BaseTestManager testManager) {
        this.page = testManager.getPage();
    }
    
    public void step1() throws Exception {
        System.out.println("📍 Navigate to Wikipedia homepage");
        page.navigate("https://www.wikipedia.org/");
    }
    
    public void step2() throws Exception {
        System.out.println("📍 Enter 'HTML' in the search box");
        page.locator("#searchInput").fill("HTML");
    }
    
    public void step3() throws Exception {
        System.out.println("📍 Click the 'Search Wikipedia' button");
        page.locator("button[type='submit']").first().click();
        page.waitForLoadState(); // Wait for navigation
    }
    
    public void step4() throws Exception {
        System.out.println("📍 Click the 'HTML' link in the search results");
        page.locator("//a[contains(normalize-space(.),'HTML')]").first().click();
        page.waitForLoadState(); // Wait for navigation
    }
    
    public void step5() throws Exception {
        System.out.println("📍 Take a screenshot of the HTML article page");
        page.screenshot(new Page.ScreenshotOptions().setPath(java.nio.file.Paths.get("wikipedia-html-article.png")));
    }
    
    public void takeScreenshot(String filename) {
        page.screenshot(new Page.ScreenshotOptions().setPath(java.nio.file.Paths.get(filename)));
        System.out.println("📸 Screenshot saved: " + filename);
    }
}