package com.automation.pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.Locator;
import com.automation.base.BaseTestManager;

public class ViewWikipediaArticlePage {
    private Page page;
    
    public ViewWikipediaArticlePage(BaseTestManager testManager) {
        this.page = testManager.getPage();
    }
    
    public void step1() throws Exception {
        System.out.println("📍 Navigate to Wikipedia homepage");
        page.navigate("https://www.wikipedia.org/");
    }

    public void step2() throws Exception {
        System.out.println("📍 Enter 'Python programming' in the search box");
        page.locator("#searchInput").first().fill("Python programming");
    }

    public void step3() throws Exception {
        System.out.println("📍 Click the search button");
        page.locator("button[type='submit']").first().click();
    }

    public void step4() throws Exception {
        System.out.println("📍 Click on the first search result titled 'Python (programming language)'");
        page.locator("//a[contains(normalize-space(.),\"Python (programming language)\")]").first().click();
    }

    public void takeScreenshot(String filename) {
        page.screenshot(new Page.ScreenshotOptions().setPath(java.nio.file.Paths.get(filename)));
        System.out.println("📸 Screenshot saved: " + filename);
    }
}