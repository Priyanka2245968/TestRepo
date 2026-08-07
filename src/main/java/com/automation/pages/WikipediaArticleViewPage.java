package com.automation.pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.Locator;
import com.automation.base.BaseTestManager;

public class WikipediaArticleViewPage {
    private Page page;
    
    public WikipediaArticleViewPage(BaseTestManager testManager) {
        this.page = testManager.getPage();
    }
    
    public void navigateToWikipediaHomepage() throws Exception {
        System.out.println("📍 Navigate to Wikipedia homepage");
        page.navigate("https://www.wikipedia.org/");
    }
    
    public void enterSearchQuery(String query) throws Exception {
        System.out.println("📍 Enter '" + query + "' in the search box");
        page.locator("#searchInput").first().fill(query);
    }
    
    public void clickSearchButton() throws Exception {
        System.out.println("📍 Click the Search button");
        page.locator("//button[contains(normalize-space(.),'Search')]").first().click();
        page.waitForTimeout(3000); // Wait for navigation after click
    }
    
    public void clickSearchResultLink(String linkText) throws Exception {
        System.out.println("📍 Click the '" + linkText + "' link in search results");
        page.locator("a[href*='" + linkText.toLowerCase() + "']").first().click();
        page.waitForTimeout(3000); // Wait for navigation after click
    }
    
    public void verifySearchBoxVisible() throws Exception {
        System.out.println("📍 Wikipedia homepage is displayed with search box visible");
        page.locator("#searchInput").first().isVisible();
    }
    
    public void verifySearchBoxValue(String expectedValue) throws Exception {
        System.out.println("📍 Search box has '" + expectedValue + "' entered");
        page.locator("#searchInput").first().waitFor(new Locator.WaitForOptions().setTimeout(5000));
        assert page.locator("#searchInput").first().inputValue().equals(expectedValue) : "Search box value mismatch";
    }
    
    public void verifySearchResultsVisible() throws Exception {
        System.out.println("📍 List of search results is displayed");
        page.locator(".search-results-container").first().isVisible();
    }
    
    public void verifySearchResultsHeading(String expectedHeading) throws Exception {
        System.out.println("📍 Search results page shows '" + expectedHeading + "' heading");
        page.locator("//h1[contains(normalize-space(.),'Search results for:')]").first().textContent().contains(expectedHeading);
    }
    
    public void verifyArticlePageTitleVisible() throws Exception {
        System.out.println("📍 Photosynthesis article page title is visible");
        page.locator("#firstHeading").first().isVisible();
    }
    
    public void verifyArticleContentVisible() throws Exception {
        System.out.println("📍 Photosynthesis article content is displayed");
        page.locator("#mw-content-text").first().isVisible();
    }
    
    public void takeScreenshot(String filename) {
        page.screenshot(new Page.ScreenshotOptions().setPath(java.nio.file.Paths.get(filename)));
        System.out.println("📸 Screenshot saved: " + filename);
    }
}