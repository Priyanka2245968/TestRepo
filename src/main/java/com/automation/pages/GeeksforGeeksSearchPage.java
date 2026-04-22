package com.automation.pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.Locator;
import com.automation.base.BaseTestManager;

public class GeeksforGeeksSearchPage {
    private Page page;
    
    public GeeksforGeeksSearchPage(BaseTestManager testManager) {
        this.page = testManager.getPage();
    }
    
    public void step1() throws Exception {
        System.out.println("📍 Open browser and go to GeeksforGeeks homepage");
        page.navigate("https://www.geeksforgeeks.org/");
    }
    
    public void step2() throws Exception {
        System.out.println("📍 Click on the Search input box");
        page.locator("a[href=\"https://www.geeksforgeeks.org/\"]").first().click();
    }
    
    public void step3() throws Exception {
        System.out.println("📍 Type Java tutorials into the search box");
        page.locator("[placeholder=\"Search...\"]").first().fill("Java tutorials");
    }
    
    public void step4() throws Exception {
        System.out.println("📍 Press Enter to submit the search");
        page.keyboard().press("Enter");
    }
    
    public void step5() throws Exception {
        System.out.println("📍 Wait for the search results page to fully load");
        page.waitForLoadState(LoadState.NETWORKIDLE);
    }
    
    public void step6() throws Exception {
        System.out.println("📍 Click the first result link");
        page.locator("a:has-text('Learn Java - A Beginners Guide for 2024 - GeeksforGeeks\"i')").first().click();
    }
    
    public void step7() throws Exception {
        System.out.println("📍 Wait for the article page to fully load");
        page.waitForLoadState(LoadState.NETWORKIDLE);
    }
    
    public void step8() throws Exception {
        System.out.println("📍 Expand the Java Basics section in the left hand side");
        // expand 'Java Basics'
    }
    
    public void takeScreenshot(String filename) {
        page.screenshot(new Page.ScreenshotOptions().setPath(java.nio.file.Paths.get(filename)));
        System.out.println("📸 Screenshot saved: " + filename);
    }
}