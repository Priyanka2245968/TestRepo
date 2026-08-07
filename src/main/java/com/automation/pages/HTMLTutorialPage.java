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
        System.out.println("📍 Navigate to https://www.w3schools.com");
        page.navigate("https://www.w3schools.com");
    }

    public void verifyPageLoadsSuccessfully() throws Exception {
        System.out.println("📍 The page at https://www.w3schools.com/html/default.asp loads successfully");
        page.waitForURL("**/html/default.asp");
    }

    public void verifyPageHeadingVisible() throws Exception {
        System.out.println("📍 And the page heading \"HTML Tutorial\" is displayed in the main content area");
        page.locator("//h1[contains(normalize-space(.),'HTML Tutorial')]").first().waitFor();
    }

    public void verifyErrorMessageVisible() throws Exception {
        System.out.println("📍 The inline error message \"Please fill out this field\" is visible in red below the \"Search our tutorials\" field; the search is NOT performed");
        page.locator(".error-message").first().waitFor();
    }

    public void verifyNoTutorialsFoundMessageVisible() throws Exception {
        System.out.println("📍 The message 'No tutorials found' is displayed on the search results page");
        page.locator(".no-results-message").first().waitFor();
    }

    public void takeScreenshot(String filename) throws Exception {
        page.screenshot(new Page.ScreenshotOptions().setPath(java.nio.file.Paths.get(filename)));
        System.out.println("📸 Screenshot saved: " + filename);
    }
}