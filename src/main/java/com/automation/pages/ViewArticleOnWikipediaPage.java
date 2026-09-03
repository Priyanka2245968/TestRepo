package com.automation.pages;

import com.automation.base.BaseTestManager;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;

public class ViewArticleOnWikipediaPage {
    private final Page page;

    public ViewArticleOnWikipediaPage(BaseTestManager testManager) {
        this.page = testManager.getPage();
    }

    public void navigateToGoogleHomepage() {
        System.out.println("📍 Navigate to https://www.google.com");
        page.navigate("https://www.google.com");
    }

    public void verifyGoogleLogoVisible() {
        System.out.println("📍 Verify Google logo is visible");
        page.waitForLoadState(LoadState.NETWORKIDLE);
        Locator logoLocator = page.locator("//img[@alt='Google'], //img[contains(@src, 'logo')], //img[contains(@alt, 'logo')]").first();
        assert logoLocator.isVisible() : "Google logo is not visible";
    }

    public void verifySearchBoxPresent() {
        System.out.println("📍 Verify search box is present");
        Locator searchBoxLocator = page.locator("textarea[name=\"q\"], input[name=\"q\"]").first();
        assert searchBoxLocator.isVisible() : "Search box is not present";
    }

    public void takeScreenshot(String filename) {
        page.screenshot(new Page.ScreenshotOptions().setPath(java.nio.file.Paths.get(filename)));
    }
}