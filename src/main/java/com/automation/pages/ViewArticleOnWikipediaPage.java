package com.automation.pages;

import com.automation.base.BaseTestManager;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;
import org.testng.Assert;

import java.nio.file.Paths;

public class ViewArticleOnWikipediaPage {

    private final Page page;
    private final Locator searchInput;
    private final Locator searchButton;

    public ViewArticleOnWikipediaPage(BaseTestManager testManager) {
        this.page = testManager.getPage();
        this.searchInput = page.locator("#searchInput");
        this.searchButton = page.locator("button[type='submit']");
    }

    public void navigateToWikipedia() {
        page.navigate("https://www.wikipedia.org/");
    }

    public void fillSearchField(String text) {
        System.out.println("\ud83d\udccd Fill 'Search Wikipedia' field with: " + text);
        searchInput.fill(text);
    }

    public void clickSearchButton() {
        System.out.println("\ud83d\udccd Click 'Search' button");
        searchButton.click();
    }

    public void waitForErrorMessage() {
        page.waitForLoadState(LoadState.NETWORKIDLE);
        page.locator(".mw-search-errorbox-caption").waitFor(new Locator.WaitForOptions().setTimeout(10000));
    }

    public void verifyErrorMessageDisplayed(String expectedMessage) {
        System.out.println("\ud83d\udccd Verify error message is displayed");
        String actualMessage = page.locator(".mw-search-errorbox-caption").textContent();
        Assert.assertTrue(actualMessage.contains(expectedMessage), "Expected error message not found");
    }

    public void takeScreenshot(String filename) {
        page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get(filename)));
    }
}