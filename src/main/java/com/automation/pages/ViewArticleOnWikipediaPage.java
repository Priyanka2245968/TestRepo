package com.automation.pages;

import com.automation.base.BaseTestManager;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;

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

    public void enterVeryLongTextInSearchField() {
        String longText = "This is a very long text string that is longer than 500 characters. " +
                           "It is used to test the behavior of the Wikipedia search when an excessively long " +
                           "search query is entered. This text should trigger an error message when submitted.";
        searchInput.fill(longText);
    }

    public void enterInvalidTextInSearchField() {
        searchInput.fill("asdfghjklzxcvbnmqwertyuiop");
    }

    public void clickSearchButton() {
        searchButton.click();
    }

    public String getErrorMessage() {
        page.waitForLoadState(LoadState.NETWORKIDLE);
        return page.locator("text=Search request is longer than the maximum allowed length").textContent();
    }

    public String getNoResultsMessage() {
        page.waitForLoadState(LoadState.NETWORKIDLE);
        return page.locator("text=There were no results matching the query").textContent();
    }

    public void takeScreenshot(String filename) {
        page.screenshot(new Page.ScreenshotOptions().setPath(java.nio.file.Paths.get(filename)));
    }
}
