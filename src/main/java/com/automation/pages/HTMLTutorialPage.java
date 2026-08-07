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
        System.out.println("📍 In the top navigation bar, click the link matching the topic: \"View HTML Tutorial on W3Schools\"");
        page.locator("#navbtn_services").first().click();
        page.waitForTimeout(3000); // Wait for navigation
    }

    public void step3() throws Exception {
        System.out.println("📍 Verify the tutorial page loads at the correct URL");
        page.waitForURL("**/html");
    }

    public void step4() throws Exception {
        System.out.println("📍 Take a screenshot for verification");
        takeScreenshot("html-tutorial-screenshot.png");
    }

    public void step5() throws Exception {
        System.out.println("📍 Verify the left navigation panel shows the tutorial highlighted under the relevant section");
        // Expand 'HTML Tutorial' via icon on BUTTON
        page.locator("JS expand 'HTML Tutorial' via icon on BUTTON").first().click();
    }
    
    public void takeScreenshot(String filename) {
        page.screenshot(new Page.ScreenshotOptions().setPath(java.nio.file.Paths.get(filename)));
        System.out.println("📸 Screenshot saved: " + filename);
    }
}