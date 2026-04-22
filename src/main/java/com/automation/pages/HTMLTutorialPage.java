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
        page.navigate("https://www.w3schools.com/");
    }

    public void step2() throws Exception {
        System.out.println("📍 Click on the 'Learn HTML' link in the HTML section");
        page.locator("#navbtn_services").first().click();
    }

    public void step3() throws Exception {
        System.out.println("📍 Click on the 'HTML Introduction' link in the left-side navigation menu");
        page.locator("a[href=\"/html/default.asp\"]").first().click();
    }

    public void step4() throws Exception {
        System.out.println("📍 Scroll down the page");
        page.locator("body").first().scrollIntoViewIfNeeded();
    }

    public void step5() throws Exception {
        System.out.println("📍 Click on the 'Start learning HTML now' button");
        page.locator("button[type='submit']").first().click();
    }

    public void takeScreenshot(String filename) {
        page.screenshot(new Page.ScreenshotOptions().setPath(java.nio.file.Paths.get(filename)));
        System.out.println("📸 Screenshot saved: " + filename);
    }
}