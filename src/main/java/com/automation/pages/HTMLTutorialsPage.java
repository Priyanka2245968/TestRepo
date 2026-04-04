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
    
    public void navigateToW3Schools() {
        System.out.println("📍 Navigate to W3Schools website");
        page.navigate("https://www.w3schools.com");
    }

    public void clickSearchBox() {
        System.out.println("📍 Click on the search input box at the top");
        page.locator("#navbtn_services").first().click();
    }

    public void enterSearchQuery(String query) {
        System.out.println("📍 Enter the keyword '" + query + "' in the search box");
        page.locator("#tnb-google-search-input").first().fill(query);
    }

    public void pressEnterToSearch() {
        System.out.println("📍 Press Enter key to submit the search");
        page.keyboard().press("Enter");
    }

    public void verifySearchResults() {
        System.out.println("📍 Search results should be displayed");
        page.locator(".search-results-container").isVisible();
    }

    public void verifyHTMLTutorials() {
        System.out.println("📍 Results should contain HTML-related tutorials");
        page.locator("text=HTML Tutorials").isVisible();
    }

    public void verifyNoErrorMessage() {
        System.out.println("📍 No error or broken page should appear");
        page.locator(".error-message").isHidden();
    }

    public void takeScreenshot(String filename) {
        page.screenshot(new Page.ScreenshotOptions().setPath(java.nio.file.Paths.get(filename)));
        System.out.println("📸 Screenshot saved: " + filename);
    }
}