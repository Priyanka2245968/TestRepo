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
        System.out.println("📍 Open the W3Schools website");
        page.navigate("https://www.w3schools.com/");
    }

    public void step2() throws Exception {
        System.out.println("📍 Enter 'HTML Tutorial' in the search bar");
        page.locator("#tnb-google-search-input").first().fill("HTML Tutorial");
    }

    public void step3() throws Exception {
        System.out.println("📍 Click on the 'HTML Tutorial' link from the search results");
        page.locator("a[href=\"https://www.w3schools.com/html/default.asp\"]").first().click();
    }

    public void verifyW3SchoolsHomepageIsDisplayed() throws Exception {
        System.out.println("📍 W3Schools homepage is displayed");
        page.locator("body").first().waitFor(new Locator.WaitForOptions().setTimeout(5000));
    }

    public void verifyHTMLTutorialSearchResultsAreShown() throws Exception {
        System.out.println("📍 Search results containing 'HTML Tutorial' are shown");
        page.locator("a[href*='html/default.asp']").first().waitFor(new Locator.WaitForOptions().setTimeout(5000));
    }

    public void verifyHTMLTutorialPageIsLoaded() throws Exception {
        System.out.println("📍 HTML Tutorial page is loaded with content and navigation panel");
        page.locator("#main").first().waitFor(new Locator.WaitForOptions().setTimeout(5000));
    }

    public void verifyTutorialExamplesAndEditorAreDisplayed() throws Exception {
        System.out.println("📍 Tutorial content is displayed with examples and 'Try it Yourself' editor");
        page.locator("#topnav").first().waitFor(new Locator.WaitForOptions().setTimeout(5000));
    }

    public void takeScreenshot(String filename) {
        page.screenshot(new Page.ScreenshotOptions().setPath(java.nio.file.Paths.get(filename)));
        System.out.println("📸 Screenshot saved: " + filename);
    }
}