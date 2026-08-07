package com.automation.pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.Locator;
import com.automation.base.BaseTestManager;

public class PreconditionPage {
    private Page page;
    
    public PreconditionPage(BaseTestManager testManager) {
        this.page = testManager.getPage();
    }
    
    public void step1() {
        System.out.println("📍 Navigate to https://www.w3schools.com");
        page.navigate("https://www.w3schools.com");
    }

    public void step2() {
        System.out.println("📍 Click on the search input box at the top");
        page.locator("#navbtn_services").first().click();
    }

    public void step3() {
        System.out.println("📍 Enter the keyword 'HTML'");
        page.locator("#tnb-google-search-input").first().fill("HTML");
    }

    public void step4() {
        System.out.println("📍 Press Enter key");
        page.keyboard().press("Enter");
    }

    public void takeScreenshot(String filename) {
        page.screenshot(new Page.ScreenshotOptions().setPath(java.nio.file.Paths.get(filename)));
        System.out.println("📸 Screenshot saved: " + filename);
    }
}