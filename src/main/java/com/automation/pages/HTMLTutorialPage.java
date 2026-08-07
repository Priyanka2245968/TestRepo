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
        System.out.println("📍 Click the 'HTML Tutorial' link in search results");
        page.locator("a[href=\"https://www.w3schools.com/html/default.asp\"]").first().click();
    }

    public void step4() throws Exception {
        System.out.println("📍 Wait for HTML Tutorial page to load");
        page.waitForURL("**/*html/*");
    }

    public void step5() throws Exception {
        System.out.println("📍 Expand the 'HTML Introduction' section in left nav");
        // expand 'HTML Introduction'
    }

    public void step6() throws Exception {
        System.out.println("📍 Click the 'Try it Yourself' button in tutorial content");
        page.locator("button[type='submit']").first().click();
    }

    public void step7() throws Exception {
        System.out.println("📍 Modify code in the interactive editor");
        page.locator("#tnb-google-search-input").first().fill("");
    }

    public void takeScreenshot(String filename) {
        page.screenshot(new Page.ScreenshotOptions().setPath(java.nio.file.Paths.get(filename)));
        System.out.println("📸 Screenshot saved: " + filename);
    }
}