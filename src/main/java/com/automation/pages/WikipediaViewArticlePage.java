package com.automation.pages;

import com.automation.base.BaseTestManager;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;

public class WikipediaViewArticlePage {
    private final Page page;
    private final Locator searchInput;
    private final Locator searchButton;
    private final Locator errorMessage;
    private final Locator noResultsMessage;

    public WikipediaViewArticlePage(BaseTestManager testManager) {
        this.page = testManager.getPage();
        this.searchInput = page.locator("#searchInput");
        this.searchButton = page.locator("//button[contains(@class,'search')]");
        this.errorMessage = page.locator("//div[@class='mw-message-box']");
        this.noResultsMessage = page.locator(".mw-search-nonefound");
    }

    public void navigateToWikipedia() {
        page.navigate("https://www.wikipedia.org/");
        page.waitForLoadState(LoadState.NETWORKIDLE);
    }

    public void enterVeryLongTextInSearchBox() {
        String longText = "a".repeat(501);
        searchInput.fill(longText);
    }

    public void enterInvalidTextInSearchBox() {
        searchInput.fill("asdf;lkj");
    }

    public void clickSearchButton() {
        searchButton.click();
    }

    public String getErrorMessage() {
        return errorMessage.textContent();
    }

    public String getNoResultsMessage() {
        return noResultsMessage.textContent();
    }

    public void takeScreenshot(String fileName) {
        page.screenshot(new Page.ScreenshotOptions().setPath(fileName));
    }
}