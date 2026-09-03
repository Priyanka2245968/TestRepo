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
        page.locator("//img[@alt='Google'], //img[contains(@src, 'logo')], //img[contains(@alt, 'logo')]").first().waitFor(new Locator.WaitForOptions().setTimeout(10000));
    }

    public void verifySearchBoxPresent() {
        System.out.println("📍 Verify search box is present");
        page.locator("textarea[name=\"q\"], input[name=\"q\"]").first().waitFor(new Locator.WaitForOptions().setTimeout(5000));
    }

    public void takeScreenshot(String filename) {
        page.screenshot(new Page.ScreenshotOptions().setPath(java.nio.file.Paths.get(filename)));
    }
}