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

        this.googleLogo = page.locator("img[alt='Google'], img[src*='logo'], img[alt*='logo']").first();
        this.searchBox = page.locator("textarea[name='q'], input[name='q']").first();
        this.searchButton = page.locator("input[name='btnK'], input[name='btnG']").first();
        this.errorMessage = page.locator(".error-message");
    }

    public void navigateToGoogleHomepage() {
        System.out.println("\ud83d\udccd Navigate to https://www.google.com");
        page.navigate("https://www.google.com");
    }

    public void verifyGoogleLogoVisible() {
        System.out.println("\ud83d\udccd Verify Google logo is visible");
        page.waitForLoadState(LoadState.NETWORKIDLE);
        googleLogo.waitFor(new Locator.WaitForOptions().setTimeoutMs(5000));
        assert googleLogo.isVisible();
    }

    public void verifySearchBoxPresent() {
        System.out.println("\ud83d\udccd Verify search box is present");
        assert searchBox.isVisible();
    }

    public void enterSearchTerm(String searchTerm) {
        System.out.println("\ud83d\udccd Enter search term: " + searchTerm);
        searchBox.fill(searchTerm);
    }

    public void clickSearchButton() {
        System.out.println("\ud83d\udccd Click search button");
        searchButton.click();
    }

    public void verifyErrorMessageDisplayed() {
        System.out.println("\ud83d\udccd Verify error message is displayed");
        assert errorMessage.isVisible();
    }

    public void takeScreenshot(String fileName) {
        System.out.println("\ud83d\udccd Take screenshot: " + fileName);
        page.screenshot(new com.microsoft.playwright.Page.ScreenshotOptions().setPath(fileName));
    }
}