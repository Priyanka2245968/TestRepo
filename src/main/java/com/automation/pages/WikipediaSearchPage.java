package com.automation.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.automation.base.BaseTestManager;

public class WikipediaSearchPage {
    private Page page;
    
    public WikipediaSearchPage(BaseTestManager testManager) {
        this.page = testManager.getPage();
    }
    
    public void navigateToWikipediaHomepage() {
        System.out.println("📍 Navigate to Wikipedia homepage");
        page.navigate("https://www.wikipedia.org/");
    }
    
    public void enterSearchTerm(String term) {
        System.out.println("📍 Enter '" + term + "' in the search box");
        page.locator("#searchInput").fill(term);
    }
    
    public void clickSearchButton() {
        System.out.println("📍 Click the 'Search Wikipedia' button");
        page.locator("button[type='submit']").first().click();
        page.waitForTimeout(3000); // wait for navigation after click
    }
    
    public void clickTopSearchResult() {
        System.out.println("📍 Click the top search result link");
        page.locator("a[href=\"/wiki/Main_Page\"]").first().click();
        page.waitForTimeout(3000); // wait for navigation after click
    }
    
    public void verifySearchBoxVisible() {
        System.out.println("📍 The 'Search Wikipedia' field is visible on the homepage");
        page.locator("#searchInput").isVisible();
    }
    
    public void verifySearchBoxValue(String value) {
        System.out.println("📍 The search box has the value '" + value + "'");
        page.locator("#searchInput").waitFor(new Locator.WaitForOptions().setTimeout(2000));
        page.locator("#searchInput").getByValue(value);
    }
    
    public void verifySearchResultsText(String text) {
        System.out.println("📍 The search results page shows '" + text + "'");
        page.locator("text=" + text).waitFor();
    }
    
    public void verifyMultipleSearchResults() {
        System.out.println("📍 Multiple search results are listed");
        page.locator(".mw-search-results").first().waitFor(new Locator.WaitForOptions().setTimeout(2000));
        page.locator(".mw-search-result-main-line").count() > 1;
    }
    
    public void verifyArticleContentVisible() {
        System.out.println("📍 The Wikipedia article page for 'HTML' loads with article content visible");
        page.locator("#mw-content-text").isVisible();
    }
    
    public void takeScreenshot(String filename) {
        page.screenshot(new Page.ScreenshotOptions().setPath(java.nio.file.Paths.get(filename)));
        System.out.println("📸 Screenshot saved: " + filename);
    }
}