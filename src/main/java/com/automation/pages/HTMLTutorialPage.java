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
        System.out.println("📍 Navigate to Wikipedia homepage");
        page.navigate("https://www.wikipedia.org/");
    }

    public void step2() throws Exception {
        System.out.println("📍 Enter 'HTML tutorial' in the search box");
        page.locator("#searchInput").first().fill("HTML tutorial");
    }

    public void step3() throws Exception {
        System.out.println("📍 Press Enter to submit the search");
        page.locator("body").first().press("Enter");
    }

    public void step4() throws Exception {
        System.out.println("📍 Click on the first search result titled 'HTML' under the 'Web results' section");
        page.locator("button[type='submit']").first().click();
    }

    public void takeScreenshot(String filename) {
        page.screenshot(new Page.ScreenshotOptions().setPath(java.nio.file.Paths.get(filename)));
        System.out.println("📸 Screenshot saved: " + filename);
    }
}