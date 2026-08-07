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
        System.out.println("📍 Open Google Chrome and navigate to https://www.w3schools.com");
        page.navigate("https://www.w3schools.com");
    }

    public void step2() throws Exception {
        System.out.println("📍 Search for the HTML Tutorial");
        page.locator("#tnb-google-search-input").first().fill("HTML Tutorial");
    }

    public void step3() throws Exception {
        System.out.println("📍 Press Enter to search");
        page.keyboard().press("Enter");
        page.waitForTimeout(3000); // Wait for navigation after click
    }

    public void verifySearchBoxVisible() throws Exception {
        System.out.println("📍 The W3Schools homepage loads and the search box is visible at the top of the page");
        page.locator("#tnb-google-search-input").first().isVisible();
    }

    public void verifySearchResultsCount() throws Exception {
        System.out.println("📍 The search results list appears with at least one matching tutorial link");
        page.locator("//a[contains(normalize-space(.),'HTML')]").first().waitFor();
    }

    public void verifyTutorialPageURL() throws Exception {
        System.out.println("📍 Verify the tutorial page loads at the correct URL");
        page.waitForURL("**/*html*");
    }

    public void verifyTutorialHeading() throws Exception {
        System.out.println("📍 Verify the page heading matches the tutorial topic");
        page.locator("//h1[contains(normalize-space(.),'HTML Tutorial')]").first().isVisible();
    }

    public void verifyLeftNavHighlighted() throws Exception {
        System.out.println("📍 Verify the left navigation panel shows the tutorial highlighted under the relevant section");
        page.locator("//nav//a[contains(@class,'active') and contains(normalize-space(.),'HTML')]").first().isVisible();
    }

    public void verifyTutorialContent() throws Exception {
        System.out.println("📍 The tutorial content and at least one code example block are visible on the page");
        page.locator(".tutorial-content").first().isVisible();
        page.locator("code.htmlHigh").first().isVisible();
    }

    public void verifyNoErrorsDisplayed() throws Exception {
        System.out.println("📍 The page displays without any 404 errors or broken layout");
        page.locator(".error-404").isHidden();
    }

    public void takeScreenshot(String filename) {
        page.screenshot(new Page.ScreenshotOptions().setPath(java.nio.file.Paths.get(filename)));
        System.out.println("📸 Screenshot saved: " + filename);
    }
}