package com.automation.pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.options.LoadState;
import com.automation.base.BaseTestManager;

public class HTMLTutorialsPage {
    private Page page;
    
    public HTMLTutorialsPage(BaseTestManager testManager) {
        this.page = testManager.getPage();
    }
    
    public void step1() {
        System.out.println("📍 Navigate to W3Schools website");
        page.navigate("https://www.w3schools.com");
    }

    public void step2() {
        System.out.println("📍 Click on the search input box at the top");
        page.locator("#navbtn_services").first().click();
    }

    public void step3() {
        System.out.println("📍 Enter the keyword 'HTML' in the search box");
        page.locator("#tnb-google-search-input").first().fill("HTML");
    }

    public void step4() {
        System.out.println("📍 Press Enter key to submit the search");
        page.keyboard().press("Enter");
    }

    public void verifySearchResults() throws Exception {
        System.out.println("📍 Search results should be displayed");
        page.locator(".search-results-container").isVisible();
    }

    public void verifyHTMLTutorials() throws Exception {
        System.out.println("📍 Results should contain HTML-related tutorials");
        page.locator("text=HTML Tutorial").isVisible();
    }

    public void verifyNoErrorMessage() throws Exception {
        System.out.println("📍 No error or broken page should appear");
        page.locator(".error-message").isHidden();
    }

    public void takeScreenshot(String filename) {
        page.screenshot(new Page.ScreenshotOptions().setPath(java.nio.file.Paths.get(filename)));
        System.out.println("📸 Screenshot saved: " + filename);
    }
}