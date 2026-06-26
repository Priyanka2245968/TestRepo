package com.automation.pages;

import com.microsoft.playwright.Page;
import com.automation.base.BaseTestManager;

public class WikipediaArticleViewPage {
    private Page page;

    public WikipediaArticleViewPage(BaseTestManager testManager) {
        this.page = testManager.getPage();
    }

    public void step1() throws Exception {
        System.out.println("📍 Navigate to Wikipedia homepage");
        page.navigate("https://www.wikipedia.org/");
    }

    public void step2() throws Exception {
        System.out.println("📍 Enter 'Photosynthesis' in the search box");
        page.locator("#searchInput").fill("Photosynthesis");
    }

    public void step3() throws Exception {
        System.out.println("📍 Click the Search button");
        page.locator("//button[contains(normalize-space(.),'Search')]").first().click();
        page.waitForTimeout(3000); // wait for navigation
    }

    public void step4() throws Exception {
        System.out.println("📍 Wait for search results page to load");
        page.waitForURL("**/wiki/Photosynthesis*");
    }

    public void step5() throws Exception {
        System.out.println("📍 Click the 'Photosynthesis' link in search results");
        page.locator("a[href='/wiki/Photosynthesis_(disambiguation)']").first().click();
        page.waitForTimeout(3000); // wait for navigation
    }

    public void takeScreenshot(String filename) throws Exception {
        page.screenshot(new Page.ScreenshotOptions().setPath(java.nio.file.Paths.get(filename)));
        System.out.println("📸 Screenshot saved: " + filename);
    }
}