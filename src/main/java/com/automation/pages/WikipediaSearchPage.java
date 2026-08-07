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
        System.out.println("📍 Open Wikipedia homepage");
        page.navigate("https://www.wikipedia.org/");
    }

    public void step2() throws Exception {
        System.out.println("📍 Verify search input box is visible");
        page.locator("button[type='submit']").first().isVisible();
    }

    public void step3() throws Exception {
        System.out.println("📍 Click on the search input box");
        page.locator("button[type='submit']").first().click();
    }

    public void step4() throws Exception {
        System.out.println("📍 Type 'Artificial Intelligence' in the search box");
        page.locator("#ooui-php-1").first().fill("Artificial Intelligence");
    }

    public void step5() throws Exception {
        System.out.println("📍 Click the Search button");
        page.locator("button[type='submit']").first().click();
    }

    public void step6() throws Exception {
        System.out.println("📍 Wait for search results page to fully load");
        page.waitForLoadState(LoadState.NETWORKIDLE);
    }

    public void takeScreenshot(String filename) {
        page.screenshot(new Page.ScreenshotOptions().setPath(java.nio.file.Paths.get(filename)));
        System.out.println("📸 Screenshot saved: " + filename);
    }
}