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
        System.out.println("📍 Navigate to https://www.w3schools.com/");
        page.navigate("https://www.w3schools.com/");
    }

    public void step2() throws Exception {
        System.out.println("📍 In the search box at the top of the page, enter 'HTML Tutorial'");
        page.locator("#tnb-google-search-input").first().fill("HTML Tutorial");
    }

    public void step3() throws Exception {
        System.out.println("📍 Click the magnifier / search icon button");
        page.locator("#navbtn_services").first().click();
    }

    public void step4() throws Exception {
        System.out.println("📍 Click the first result link labelled 'HTML Tutorial'");
        page.locator("a[href=\"/html/default.asp\"]").first().click();
        page.waitForURL("**/html/default.asp");
    }

    public void step5() throws Exception {
        System.out.println("📍 Scroll down to the section labelled 'How to Use This Tutorial'");
        page.waitForTimeout(2000); // Wait for page to load
    }

    public void takeScreenshot(String filename) {
        page.screenshot(new Page.ScreenshotOptions().setPath(java.nio.file.Paths.get(filename)));
        System.out.println("📸 Screenshot saved: " + filename);
    }
}