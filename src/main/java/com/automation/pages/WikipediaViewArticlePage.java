package com.automation.pages;

import com.automation.base.BaseTestManager;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;
import org.testng.Assert;

public class WikipediaViewArticlePage {
    private final Page page;
    private final Locator searchInput;
    private final Locator searchButton;
    private final Locator submitButton;

    public WikipediaViewArticlePage(BaseTestManager testManager) {
        this.page = testManager.getPage();
        this.searchInput = page.locator("#searchInput");
        this.searchButton = page.locator("//button[contains(normalize-space(.),'search')]");
        this.submitButton = page.locator("button[type='submit']");
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

    public void verifyErrorMessageForLongSearch() {
        page.waitForLoadState(LoadState.NETWORKIDLE);
        String errorMessage = page.locator("//div[@class='mw-message-box']").textContent();
        Assert.assertTrue(errorMessage.contains("Search request is longer than the maximum allowed length"));
    }

    public void verifyNoResultsMessage() {
        page.waitForLoadState(LoadState.NETWORKIDLE);
        String noResultsMessage = page.locator("//p[@class='mw-search-nonefound']").textContent();
        Assert.assertTrue(noResultsMessage.contains("There were no results matching the query"));
    }

    public void takeScreenshot(String filename) {
        page.screenshot(new Page.ScreenshotOptions().setPath(java.nio.file.Paths.get(filename)));
    }
}