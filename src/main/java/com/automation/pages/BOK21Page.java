package com.automation.pages;

import com.automation.base.BaseTestManager;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;

import java.nio.file.Paths;

public class BOK21Page {
    private final Page page;
    private final Locator searchInput;
    private final Locator searchButton;
    private final Locator errorMessage;
    private final Locator noResultsMessage;

    public BOK21Page(BaseTestManager testManager) {
        this.page = testManager.getPage();
        this.searchInput = page.locator("#searchInput");
        this.searchButton = page.locator("button[type='submit']");
        this.errorMessage = page.locator(".mw-message-box");
        this.noResultsMessage = page.locator(".mw-search-nonefound");
    }

    public void navigateToWikipedia() {
        page.navigate("https://www.wikipedia.org/");
    }

    public void enterVeryLongTextInSearchField() {
        String longText = "A very long text with more than 500 characters...".repeat(50);
        searchInput.fill(longText);
    }

    public void clickSearchButton() {
        searchButton.click();
    }

    public void waitForErrorMessage() {
        page.waitForLoadState(LoadState.NETWORKIDLE);
        errorMessage.waitFor(new Locator.WaitForOptions().setTimeout(5000));
    }

    public String getErrorMessage() {
        return errorMessage.textContent();
    }

    public void enterInvalidTextInSearchField() {
        searchInput.fill("!@#$%^&*()_+");
    }

    public void waitForNoResultsMessage() {
        page.waitForLoadState(LoadState.NETWORKIDLE);
        noResultsMessage.waitFor(new Locator.WaitForOptions().setTimeout(5000));
    }

    public String getNoResultsMessage() {
        return noResultsMessage.textContent();
    }

    public void takeScreenshot(String fileName) {
        page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get(fileName)));
    }
}
