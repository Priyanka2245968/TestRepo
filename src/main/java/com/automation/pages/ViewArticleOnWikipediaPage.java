package com.automation.pages;

import com.automation.base.BaseTestManager;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;

import java.nio.file.Paths;

public class ViewArticleOnWikipediaPage {
    private final Page page;
    private final Locator searchInput;
    private final Locator searchButton;
    private final Locator errorMessage;
    private final Locator noResultsMessage;

    public ViewArticleOnWikipediaPage(BaseTestManager testManager) {
        this.page = testManager.getPage();
        this.searchInput = page.locator("#searchInput");
        this.searchButton = page.locator("button[type='submit']");
        this.errorMessage = page.locator(".mw-message-box");
        this.noResultsMessage = page.locator(".mw-search-nonefound");
    }

    public void navigateToWikipedia() {
        page.navigate("https://www.wikipedia.org/");
        page.waitForLoadState(LoadState.NETWORKIDLE);
    }

    public void enterSearchText(String text) {
        searchInput.fill(text);
    }

    public void clickSearchButton() {
        searchButton.click();
        page.waitForLoadState(LoadState.NETWORKIDLE);
    }

    public void waitForErrorMessage() {
        errorMessage.waitFor(new Locator.WaitForOptions().setState(com.microsoft.playwright.options.WaitForSelectorState.VISIBLE));
    }

    public String getErrorMessage() {
        return errorMessage.textContent();
    }

    public void waitForNoResultsMessage() {
        noResultsMessage.waitFor(new Locator.WaitForOptions().setState(com.microsoft.playwright.options.WaitForSelectorState.VISIBLE));
    }

    public Locator getNoResultsMessage() {
        return noResultsMessage;
    }

    public void takeScreenshot(String fileName) {
        page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get(fileName)));
    }
}