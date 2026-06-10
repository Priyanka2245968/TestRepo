package com.automation.pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.Locator;
import com.automation.base.BaseTestManager;

public class WikipediaArticleSearchPage {
    private Page page;
    
    public WikipediaArticleSearchPage(BaseTestManager testManager) {
        this.page = testManager.getPage();
    }
    
    public void step1() throws Exception {
        System.out.println("📍 Navigate to Wikipedia homepage");
        page.navigate("https://www.wikipedia.org/");
        page.waitForTimeout(2000);
    }

    public void step2() throws Exception {
        System.out.println("📍 Enter 'Python (programming language)' in the search box");
        page.locator("#searchInput").first().fill("Python (programming language)");
    }

    public void step3() throws Exception {
        System.out.println("📍 Click the 'Search Wikipedia' button");
        page.locator("button[type='submit']").first().click();
        page.waitForTimeout(3000);  // Wait for navigation
    }

    public void step4() throws Exception {
        System.out.println("📍 Click the 'Python (programming language)' link in search results");
        page.locator("a[href='/wiki/Category:Python_(programming_language)']").first().click();
        page.waitForTimeout(3000);  // Wait for navigation
    }

    public void takeScreenshot(String filename) {
        page.screenshot(new Page.ScreenshotOptions().setPath(java.nio.file.Paths.get(filename)));
        System.out.println("📸 Screenshot saved: " + filename);
    }
}