package com.automation.pages;

import com.automation.base.BaseTestManager;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;
import org.testng.Assert;

public class ViewArticleOnWikipediaPage {
    private final Page page;

    public ViewArticleOnWikipediaPage(BaseTestManager testManager) {
        this.page = testManager.getPage();
    }

    public void navigateToWikipediaHomepage() {
        page.navigate("https://www.wikipedia.org/");
    }

    public void searchForTerm(String term) {
        System.out.println("📍 Entering search term: " + term);
        page.locator("#searchInput").fill(term);
    }

    public void clickSearchButton() {
        System.out.println("📍 Clicking 'Search' button");
        page.locator("button[type='submit']").click();
        page.waitForLoadState(LoadState.NETWORKIDLE);
    }

    public void clickSearchResultLink(String selector) {
        System.out.println("📍 Clicking search result link: " + selector);
        page.locator(selector).first().click();
        page.waitForLoadState(LoadState.NETWORKIDLE);
    }

    public void verifyErrorMessageDisplayed(String expectedMessage) {
        String actualMessage = page.locator(".mw-search-errorbox-caption").textContent();
        System.out.println("📍 Verifying error message: " + expectedMessage);
        Assert.assertTrue(actualMessage.contains(expectedMessage), "Error message does not match expected: " + expectedMessage);
    }

    public void takeScreenshot(String filename) {
        page.screenshot(new Page.ScreenshotOptions().setPath(java.nio.file.Paths.get(filename)));
    }
}
