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
        System.out.println("📍 Navigate to the Wikipedia homepage");
        page.navigate("https://www.wikipedia.org");
    }

    public void step2() throws Exception {
        System.out.println("📍 Enter the search term 'Artificial Intelligence' in the search field");
        page.locator("#searchInput").first().fill("Artificial Intelligence");
    }

    public void step3() throws Exception {
        System.out.println("📍 Click the Search button");
        page.locator("button[type='submit']").first().click();
    }

    public void takeScreenshot(String filename) {
        page.screenshot(new Page.ScreenshotOptions().setPath(java.nio.file.Paths.get(filename)));
        System.out.println("📸 Screenshot saved: " + filename);
    }
}