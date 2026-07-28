package com.automation.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.automation.base.BaseTestManager;

public class WikipediaArticleViewPage {
    private Page page;
    
    public WikipediaArticleViewPage(BaseTestManager testManager) {
        this.page = testManager.getPage();
    }
    
    public void step1() throws Exception {
        System.out.println("📍 Navigate to Wikipedia homepage");
        page.navigate("https://www.wikipedia.org");
    }
    
    public void step2() throws Exception {
        System.out.println("📍 Enter 'HTML Tutorial' in the search box");
        page.locator("#searchInput").first().fill("HTML Tutorial");
    }
    
    public void step3() throws Exception {
        System.out.println("📍 Click the Search button");
        page.locator("//button[contains(normalize-space(.),'Search')]").first().click();
        page.waitForLoadState("networkidle"); // wait for navigation
    }
    
    public void step4() throws Exception {
        System.out.println("📍 Click the first search result link titled 'HTML Tutorial'");
        page.locator("//a[contains(., 'HTML Tutorial')]").first().click();
        page.waitForLoadState("networkidle"); // wait for navigation
    }
    
    public void takeScreenshot(String filename) {
        page.screenshot(new Page.ScreenshotOptions().setPath(java.nio.file.Paths.get(filename)));
        System.out.println("📸 Screenshot saved: " + filename);
    }
}