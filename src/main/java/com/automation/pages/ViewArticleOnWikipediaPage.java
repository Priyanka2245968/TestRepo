package com.automation.pages;

import com.automation.base.BaseTestManager;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;

import java.nio.file.Paths;

public class ViewArticleOnWikipediaPage {
    private final BaseTestManager testManager;
    private final Locator searchInput;
    private final Locator searchButton;
    private final Locator errorMessage;
    private final Locator noResultsMessage;

    public ViewArticleOnWikipediaPage(BaseTestManager testManager) {
        this.testManager = testManager;
        this.searchInput = testManager.getPage().locator("#searchInput");
        this.searchButton = testManager.getPage().locator("button[type='submit']");
        this.errorMessage = testManager.getPage().locator(".mw-message-box");
        this.noResultsMessage = testManager.getPage().locator(".mw-search-nonefound");
    }

    public void navigateToWikipedia() {
        testManager.getPage().navigate("https://www.wikipedia.org/");
        testManager.getPage().waitForLoadState(LoadState.NETWORKIDLE);
    }

    public void enterSearchText(String text) {
        searchInput.fill(text);
    }

    public void clickSearchButton() {
        searchButton.click();
    }

    public void waitForErrorMessage() {
        errorMessage.waitFor();
    }

    public String getErrorMessage() {
        return errorMessage.textContent();
    }

    public void waitForNoResultsMessage() {
        noResultsMessage.waitFor();
    }

    public Locator getNoResultsMessage() {
        return noResultsMessage;
    }

    public void takeScreenshot(String fileName) {
        Page page = testManager.getPage();
        page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get(fileName)));
    }
}