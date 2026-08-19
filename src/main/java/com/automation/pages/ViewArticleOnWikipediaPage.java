package com.automation.pages;

import com.automation.base.BaseTestManager;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.options.LoadState;

public class ViewArticleOnWikipediaPage {
    private final BaseTestManager testManager;
    private final com.microsoft.playwright.Page page;

    private final Locator googleLogo;
    private final Locator searchBox;
    private final Locator searchButton;
    private final Locator errorMessage;

    public ViewArticleOnWikipediaPage(BaseTestManager testManager) {
        this.testManager = testManager;
        this.page = testManager.getPage();

        this.googleLogo = page.locator("//img[@alt='Google'], //img[contains(@src, 'logo')], //img[contains(@alt, 'logo')]").first();
        this.searchBox = page.locator("textarea[name=\"q\"], input[name=\"q\"]").first();
        this.searchButton = page.locator("input[name=\"btnK\"], input[name=\"btnG\"]").first();
        this.errorMessage = page.locator(".error-message");
    }

    public void navigateToGoogleHomepage() {
        System.out.println("📍 Navigate to https://www.google.com");
        page.navigate("https://www.google.com");
    }

    public void verifyGoogleLogoVisible() {
        System.out.println("📍 Verify Google logo is visible");
        page.waitForLoadState(LoadState.NETWORKIDLE);
        googleLogo.waitFor(new Locator.WaitForOptions().setTimeout(10000));
    }

    public void verifySearchBoxPresent() {
        System.out.println("📍 Verify search box is present");
        searchBox.waitFor(new Locator.WaitForOptions().setTimeout(5000));
    }

    public void enterSearchTerm(String term) {
        System.out.println("📍 Enter search term: " + term);
        searchBox.fill(term);
    }

    public void clickSearchButton() {
        System.out.println("📍 Click search button");
        searchButton.click();
    }

    public void verifyErrorMessageDisplayed() {
        System.out.println("📍 Verify error message is displayed");
        errorMessage.waitFor(new Locator.WaitForOptions().setTimeout(5000));
        assert errorMessage.isVisible();
    }

    public void takeScreenshot(String filename) {
        System.out.println("📸 Taking screenshot: " + filename);
        page.screenshot(new com.microsoft.playwright.Page.ScreenshotOptions().setPath(java.nio.file.Paths.get(filename)));
    }
}