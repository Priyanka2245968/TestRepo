package com.automation.pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.Locator;
import com.automation.base.BaseTestManager;

public class WikipediaArticleViewPage {
    private Page page;
    
    public WikipediaArticleViewPage(BaseTestManager testManager) {
        this.page = testManager.getPage();
    }
    
    public void verifySearchBoxVisible() throws Exception {
        System.out.println("📍 Wikipedia homepage is displayed with search box visible");
        page.locator("#searchInput").isVisible();
    }
    
    public void enterSearchText(String text) throws Exception {
        System.out.println("📍 Enter '" + text + "' in the search box");
        page.locator("#searchInput").fill(text);
    }
    
    public void verifySearchBoxValue(String value) throws Exception {
        System.out.println("📍 Search box has '" + value + "' entered");
        page.locator("#searchInput").waitFor(new Locator.WaitForOptions().setHasValue(value));
    }
    
    public void clickSearchButton() throws Exception {
        System.out.println("📍 Click the Search button");
        page.locator("//button[contains(normalize-space(.),'Search')]").first().click();
        page.waitForTimeout(3000); // Wait for navigation after click
    }
    
    public void verifySearchResultsVisible() throws Exception {
        System.out.println("📍 List of search results is displayed");
        page.locator(".search-results").isVisible();
    }
    
    public void verifySearchResultsHeading(String heading) throws Exception {
        System.out.println("📍 Search results page shows '" + heading + "' heading");
        page.locator("//h1[contains(normalize-space(.),'Search results for:')]").textContent().contains(heading);
    }
    
    public void clickArticleLink(String linkText) throws Exception {
        System.out.println("📍 Click the '" + linkText + "' link in search results");
        page.locator("a[href*='" + linkText + "']").first().click();
        page.waitForTimeout(3000); // Wait for navigation after click
    }
    
    public void verifyArticleTitleVisible() throws Exception {
        System.out.println("📍 Photosynthesis article title is visible");
        page.locator("//h1[contains(normalize-space(.),'Photosynthesis')]").isVisible();
    }
    
    public void verifyArticleContentVisible() throws Exception {
        System.out.println("📍 Photosynthesis article content is displayed");
        page.locator(".mw-parser-output").isVisible();
    }
    
    public void takeScreenshot(String filename) {
        page.screenshot(new Page.ScreenshotOptions().setPath(java.nio.file.Paths.get(filename)));
        System.out.println("📸 Screenshot saved: " + filename);
    }
}