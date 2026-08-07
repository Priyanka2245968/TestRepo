package com.automation.pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.options.LoadState;
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
        System.out.println("📍 Click on the 'Learn HTML' button on the homepage");
        page.locator("#navbtn_services").first().click();
    }

    public void step3() throws Exception {
        System.out.println("📍 Click on the 'HTML Tutorial' link in the left navigation menu");
        page.locator("a[href=\"/html/default.asp\"]").first().click();
    }

    public void step4() throws Exception {
        System.out.println("📍 Attempt to navigate to an invalid tutorial URL by modifying the URL path");
        page.locator("#tnb-google-search-input").fill("invalid tutorial URL");
    }

    public void step5() throws Exception {
        System.out.println("📍 Press Enter to navigate to the invalid URL");
        page.keyboard().press("Enter");
    }

    public void step6() throws Exception {
        System.out.println("📍 Restore the correct URL path to the HTML Tutorial page");
        page.locator("#tnb-google-search-input").fill("https://www.w3schools.com/html/default.asp");
    }

    public void step7() throws Exception {
        System.out.println("📍 Press Enter to navigate to the correct HTML Tutorial page");
        page.keyboard().press("Enter");
    }

    public void step8() throws Exception {
        System.out.println("📍 Click on the 'Next' button to move to the next tutorial step");
        page.locator("button[type='submit']").first().click();
    }

    public void step9() throws Exception {
        System.out.println("📍 Click on the 'Previous' button to move back to the previous tutorial step");
        page.locator("button[type='submit']").first().click();
    }

    public void step10() throws Exception {
        System.out.println("📍 Scroll down the page");
        page.keyboard().press("PageDown");
    }

    public void step11() throws Exception {
        System.out.println("📍 Scroll up the page");
        page.keyboard().press("PageUp");
    }

    public void step12() throws Exception {
        System.out.println("📍 Click on the 'Try it Yourself' button to open the interactive editor");
        page.locator("button[type='submit']").first().click();
    }

    public void takeScreenshot(String filename) {
        page.screenshot(new Page.ScreenshotOptions().setPath(java.nio.file.Paths.get(filename)));
        System.out.println("📸 Screenshot saved: " + filename);
    }
}