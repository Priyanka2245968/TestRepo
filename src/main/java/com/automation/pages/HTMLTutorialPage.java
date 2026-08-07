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
        System.out.println("📍 Enter 'HTML Tutorial' in the search box");
        page.locator("#tnb-google-search-input").first().fill("HTML Tutorial");
    }

    public void step3() throws Exception {
        System.out.println("📍 Click the search button");
        page.locator("button:has-text('search'), input[type='submit'][value*='search']").first().click();
    }

    public void step4() throws Exception {
        System.out.println("📍 Click the first search result link labelled 'HTML Tutorial'");
        page.locator("a:has-text('HTML Tutorial')").first().click();
    }

    public void step5() throws Exception {
        System.out.println("📍 Scroll down to view the left navigation panel");
        page.waitForTimeout(2000); // Wait for page load
    }

    public void step6() throws Exception {
        System.out.println("📍 Expand the HTML section in the left navigation panel");
        // JS expand 'HTML' via container-dispatch on H3
    }

    public void takeScreenshot(String filename) {
        page.screenshot(new Page.ScreenshotOptions().setPath(java.nio.file.Paths.get(filename)));
        System.out.println("📸 Screenshot saved: " + filename);
    }
}