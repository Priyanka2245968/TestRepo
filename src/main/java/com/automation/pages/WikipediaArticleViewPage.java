package com.automation.pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.Locator;
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
        System.out.println("📍 Enter 'HTML' in the search box");
        page.locator("#searchInput").first().fill("HTML");
    }

    public void step3() throws Exception {
        System.out.println("📍 Click the search button");
        page.locator("//button[contains(normalize-space(.),\"Search\")]").first().click();
        page.waitForTimeout(3000); // Wait for navigation
    }

    public void step4() throws Exception {
        System.out.println("📍 Wait for search results page to load");
        page.waitForURL("**/*");
    }

    public void step5() throws Exception {
        System.out.println("📍 Click the 'HTML' link in search results");
        page.locator("//button[contains(normalize-space(.),\"HTML\")]").first().click();
        page.waitForTimeout(3000); // Wait for navigation
    }

    public void step6() throws Exception {
        System.out.println("📍 Wait for HTML article page to load");
        page.waitForURL("**/*");
    }

    public void takeScreenshot(String filename) {
        page.screenshot(new Page.ScreenshotOptions().setPath(java.nio.file.Paths.get(filename)));
        System.out.println("📸 Screenshot saved: " + filename);
    }
}