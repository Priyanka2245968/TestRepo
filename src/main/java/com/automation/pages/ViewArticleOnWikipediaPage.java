package com.automation.pages;

import com.automation.base.BaseTestManager;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.options.LoadState;

public class ViewArticleOnWikipediaPage {
    private final BaseTestManager testManager;
    private final com.microsoft.playwright.Page page;

    private final Locator searchInput;
    private final Locator searchButton;
    private final Locator articleTitle;
    private final Locator errorMessage;

    public ViewArticleOnWikipediaPage(BaseTestManager testManager) {
        this.testManager = testManager;
        this.page = testManager.getPage();

        this.searchInput = page.locator("input[name='q']");
        this.searchButton = page.locator("input[name='btnK']");
        this.articleTitle = page.locator("h1");
        this.errorMessage = page.locator("#recaptcha-error");
    }

    public void navigateToGoogleHomepage() {
        System.out.println("📍 Navigate to Google homepage");
        page.navigate("https://www.google.com");
    }

    public void enterSearchTerm(String term) {
        System.out.println("📍 Enter search term: " + term);
        searchInput.fill(term);
    }

    public void clickSearchButton() {
        System.out.println("📍 Click search button");
        searchButton.click();
        page.waitForLoadState(LoadState.NETWORKIDLE);
    }

    public void verifyArticlePageLoaded() {
        System.out.println("📍 Verify article page loaded");
        articleTitle.waitFor(new Locator.WaitForOptions().setTimeout(10000));
        org.testng.Assert.assertTrue(articleTitle.isVisible(), "Article title is not visible");
    }

    public void verifyErrorMessageDisplayed() {
        System.out.println("📍 Verify error message is displayed");
        errorMessage.waitFor(new Locator.WaitForOptions().setTimeout(5000));
        org.testng.Assert.assertTrue(errorMessage.isVisible(), "Error message is not visible");
    }

    public void takeScreenshot(String filename) {
        System.out.println("📍 Taking screenshot: " + filename);
        page.screenshot(new com.microsoft.playwright.Page.ScreenshotOptions().setPath(java.nio.file.Paths.get(filename)));
    }
}