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
        System.out.println("📍 Navigate to W3Schools homepage");
        page.navigate("https://www.w3schools.com");
    }

    public void step2() throws Exception {
        System.out.println("📍 Click the 'HTML' link in the top navigation bar");
        page.locator("//a[contains(normalize-space(.),'HTML')]").first().click();
        page.waitForTimeout(3000); // Wait for navigation after click
    }

    public void step3() throws Exception {
        System.out.println("📍 Wait for navigation to complete");
        page.waitForURL("**/html/default.asp");
    }

    public void verifyHTMLTutorialPageLoaded() throws Exception {
        System.out.println("📍 Verify the HTML Tutorial page loads at https://www.w3schools.com/html/default.asp");
        page.waitForURL("**/html/default.asp");
    }

    public void verifyHTMLTutorialHeadingDisplayed() throws Exception {
        System.out.println("📍 Verify the page heading 'HTML Tutorial' is displayed in the main content area");
        page.locator("//h1[contains(normalize-space(.),'HTML Tutorial')]").first().waitFor(new Locator.WaitForOptions().setTimeout(5000));
    }

    public void verifyHTMLHomeHighlighted() throws Exception {
        System.out.println("📍 Verify the left navigation panel shows 'HTML HOME' highlighted under the HTML section");
        page.locator("//a[contains(normalize-space(.),'HTML HOME')][contains(@class,'active')]").first().waitFor(new Locator.WaitForOptions().setTimeout(5000));
    }

    public void takeScreenshot(String filename) {
        page.screenshot(new Page.ScreenshotOptions().setPath(java.nio.file.Paths.get(filename)));
        System.out.println("📸 Screenshot saved: " + filename);
    }
}