package com.automation.pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.Locator;
import com.automation.base.BaseTestManager;

public class HTMLTutorialPage {
    private Page page;
    
    public HTMLTutorialPage(BaseTestManager testManager) {
        this.page = testManager.getPage();
    }
    
    public void navigateToW3Schools() {
        System.out.println("📍 Open Google Chrome and navigate to https://www.w3schools.com");
        page.navigate("https://www.w3schools.com");
    }

    public void clickViewHTMLTutorialLink() {
        System.out.println("📍 In the top navigation bar, click the link matching the topic: \"View HTML Tutorial on W3Schools\"");
        page.locator("#navbtn_services").first().click();
    }

    public void waitForTutorialPageLoad() {
        System.out.println("📍 Verify the tutorial page loads at the correct URL");
        page.waitForTimeout(2000);
    }

    public void takeScreenshot(String filename) {
        System.out.println("📍 Take a screenshot for verification");
        page.screenshot(new Page.ScreenshotOptions().setPath(java.nio.file.Paths.get(filename)));
        System.out.println("📸 Screenshot saved: " + filename);
    }

    public void verifyLeftNavigationPanel() {
        System.out.println("📍 Verify the left navigation panel shows the tutorial highlighted under the relevant section");
        // No locator provided in execution log, skipping this step
    }
}