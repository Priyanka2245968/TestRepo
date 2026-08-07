package com.automation.pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.Locator;
import com.automation.base.BaseTestManager;

public class WikipediaSearchPage {
    private Page page;
    
    public WikipediaSearchPage(BaseTestManager testManager) {
        this.page = testManager.getPage();
    }
    
    public void step1() throws Exception {
        System.out.println("📍 Navigate to Wikipedia homepage");
        page.navigate("https://www.wikipedia.org/");
    }

    public void step2() throws Exception {
        System.out.println("📍 Enter 'Python programming' in the search box");
        page.locator("#searchInput").first().fill("Python programming");
    }

    public void step3() throws Exception {
        System.out.println("📍 Press Enter to submit the search");
        page.locator("body").first().press("Enter");
    }

    public void step4() throws Exception {
        System.out.println("📍 Click the first search result link titled 'Python (programming language)'");
        page.locator("#vector-main-menu-dropdown-checkbox").first().click();
    }

    public void takeScreenshot(String filename) {
        page.screenshot(new Page.ScreenshotOptions().setPath(java.nio.file.Paths.get(filename)));
        System.out.println("📸 Screenshot saved: " + filename);
    }
}