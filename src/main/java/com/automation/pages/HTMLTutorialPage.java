package com.automation.pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.Locator;
import com.automation.base.BaseTestManager;

public class HTMLTutorialPage {
    private Page page;
    
    public HTMLTutorialPage(BaseTestManager testManager) {
        this.page = testManager.getPage();
    }
    
    public void step1() throws Exception {
        System.out.println("📍 Open Google Chrome and navigate to https://www.w3schools.com");
        page.navigate("https://www.w3schools.com");
    }

    public void step2() throws Exception {
        System.out.println("📍 Search for the HTML Tutorial");
        page.locator("#tnb-google-search-input").first().fill("HTML Tutorial");
    }

    public void step3() throws Exception {
        System.out.println("📍 Press Enter to search");
        page.locator("body").first().press("Enter");
        page.waitForTimeout(3000); // Wait for navigation after click
    }
    
    public void takeScreenshot(String filename) {
        page.screenshot(new Page.ScreenshotOptions().setPath(java.nio.file.Paths.get(filename)));
        System.out.println("📸 Screenshot saved: " + filename);
    }
}