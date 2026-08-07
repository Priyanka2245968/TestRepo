package com.automation.pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.Locator;
import com.automation.base.BaseTestManager;

public class AstronomyWikiPage {
    private Page page;
    
    public AstronomyWikiPage(BaseTestManager testManager) {
        this.page = testManager.getPage();
    }
    
    public void step1() throws Exception {
        System.out.println("📍 Navigate to Wikipedia homepage");
        page.navigate("https://www.wikipedia.org/");
    }

    public void step2() throws Exception {
        System.out.println("📍 Enter 'Astronomy' in the search box");
        page.locator("#searchInput").first().fill("Astronomy");
    }

    public void step3() throws Exception {
        System.out.println("📍 Click the 'Search' button");
        page.locator("//button[contains(normalize-space(.),'Search')]").first().click();
        page.waitForTimeout(3000); // Wait for navigation
    }

    public void step4() throws Exception {
        System.out.println("📍 Click the 'Astronomy' link in the search results");
        page.locator("//button[contains(normalize-space(.),'Astronomy')]").first().click();
    }

    public void takeScreenshot(String filename) {
        page.screenshot(new Page.ScreenshotOptions().setPath(java.nio.file.Paths.get(filename)));
        System.out.println("📸 Screenshot saved: " + filename);
    }
}