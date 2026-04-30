package com.automation.pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.options.WaitForSelectorState;
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
        page.locator("a[href='/html/default.asp']").first().click();
        page.waitForTimeout(3000); // Wait for new page to fully load after navigation
    }

    public void step3() throws Exception {
        System.out.println("📍 Wait for search results to load");
        page.waitForTimeout(2000);
    }

    public void step4() throws Exception {
        System.out.println("📍 Click on the tutorial link in the search results");
        page.locator("//button[contains(normalize-space(.),'Tutorial')]").first().click();
        page.waitForTimeout(3000); // Wait for new page to fully load after navigation
    }

    public void step5() throws Exception {
        System.out.println("📍 Wait for tutorial page to load");
        page.waitForTimeout(2000);
    }

    public void step6() throws Exception {
        System.out.println("📍 Take a screenshot of the tutorial page");
        page.screenshot(new Page.ScreenshotOptions().setPath(java.nio.file.Paths.get("tutorial-screenshot-" + System.currentTimeMillis() + ".png")));
    }

    public void step7() throws Exception {
        System.out.println("📍 Expand the HTML Tutorial section in the left navigation panel");
        // expand 'HTML Tutorial'
    }

    public void takeScreenshot(String filename) {
        page.screenshot(new Page.ScreenshotOptions().setPath(java.nio.file.Paths.get(filename)));
        System.out.println("📸 Screenshot saved: " + filename);
    }
}