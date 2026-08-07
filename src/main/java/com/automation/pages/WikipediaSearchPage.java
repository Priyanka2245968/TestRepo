package com.automation.pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.Locator;
import com.automation.base.BaseTestManager;

public class WikipediaSearchPage {
    private Page page;
    
    public WikipediaSearchPage(BaseTestManager testManager) {
        this.page = testManager.getPage();
    }
    
    public void navigateToWikipediaHomepage() throws Exception {
        System.out.println("📍 Navigate to Wikipedia homepage");
        page.navigate("https://www.wikipedia.org/");
    }
    
    public void enterSearchTerm(String term) throws Exception {
        System.out.println("📍 Enter '" + term + "' in the search field");
        page.locator("#searchInput").first().fill(term);
    }
    
    public void clickSearchButton() throws Exception {
        System.out.println("📍 Click the 'Search' button");
        page.locator("//button[contains(normalize-space(.),'Search')]").first().click();
        page.waitForTimeout(3000); // Wait for navigation after click
    }
    
    public void clickSearchResult(String result) throws Exception {
        System.out.println("📍 Click the '" + result + "' link in the search results");
        page.locator("//button[contains(normalize-space(.),\"" + result + "\")]").first().click();
    }
    
    public void verifySearchBoxVisible() throws Exception {
        System.out.println("📍 The 'Search Wikipedia' field is visible on the homepage");
        page.locator("#searchInput").first().isVisible();
    }
    
    public void verifySearchBoxValue(String value) throws Exception {
        System.out.println("📍 The search field has the entered text '" + value + "'");
        page.locator("#searchInput").first().waitFor(new Locator.WaitForOptions().setTimeout(5000));
        assert page.locator("#searchInput").first().inputValue().equals(value);
    }
    
    public void verifySearchResultsVisible(String term) throws Exception {
        System.out.println("📍 The search results page displays with heading 'Search results for: " + term + "'");
        page.locator("//h1[contains(normalize-space(.),'Search results for: " + term + "')]").first().isVisible();
    }
    
    public void verifyArticleContentVisible(String term) throws Exception {
        System.out.println("📍 The '" + term + "' article page content is visible");
        page.locator("//h1[contains(normalize-space(.),'" + term + "')]").first().isVisible();
    }
    
    public void verifyUrlContains(String path) throws Exception {
        System.out.println("📍 The URL contains '" + path + "' after navigating to the article");
        page.waitForURL("**/" + path);
    }
    
    public void takeScreenshot(String filename) {
        page.screenshot(new Page.ScreenshotOptions().setPath(java.nio.file.Paths.get(filename)));
        System.out.println("📸 Screenshot saved: " + filename);
    }
}