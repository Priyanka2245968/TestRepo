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
        System.out.println("📍 Click on the 'TUTORIALS' link in the top navigation menu");
        page.locator("#navbtn_tutorials").first().click();
    }

    public void step3() throws Exception {
        System.out.println("📍 In the sidebar under 'Content', expand the section");
        // expand 'Content'
    }

    public void step4() throws Exception {
        System.out.println("📍 Click on the 'HTML Tutorial' link");
        page.locator("#navbtn_services").first().click();
    }

    public void step5() throws Exception {
        System.out.println("📍 On the HTML Tutorial page, click the 'Start learning HTML now' button");
        page.locator("a[href=\"/html/default.asp\"]").first().click();
    }

    public void takeScreenshot(String filename) {
        page.screenshot(new Page.ScreenshotOptions().setPath(java.nio.file.Paths.get(filename)));
        System.out.println("📸 Screenshot saved: " + filename);
    }
}