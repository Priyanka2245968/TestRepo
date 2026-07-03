package com.automation.pages;

import com.automation.base.BaseTestManager;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;

import java.nio.file.Paths;

public class WikipediaArticleViewPage {
    private final Page page;
    private final Locator searchInput;
    private final Locator searchButton;
    private final Locator blankSearchErrorMessage;
    private final Locator noResultsMessage;

    public WikipediaArticleViewPage(BaseTestManager testManager) {
        this.page = testManager.getPage();
        this.searchInput = page.locator("#searchInput");
        this.searchButton = page.locator("button[type='submit']");
        this.blankSearchErrorMessage = page.locator("//div[contains(@class,'mw-message-box')]");
        this.noResultsMessage = page.locator("//div[contains(@class,'mw-search-nonefound')]");
    }

    public void navigateToWikipedia() {
        page.navigate("https://www.wikipedia.org/");
    }

    public void searchForArticle(String query) {
        searchInput.fill(query);
    }

    public void clickSearchButton() {
        searchButton.click();
    }

    public void waitForArticleLoad() {
        page.waitForLoadState(LoadState.NETWORKIDLE);
    }

    public Page getPage() {
        return page;
    }

    public Locator getBlankSearchErrorMessage() {
        return blankSearchErrorMessage;
    }

    public Locator getNoResultsMessage() {
        return noResultsMessage;
    }

    public void waitForNoResultsPage() {
        page.waitForLoadState(LoadState.NETWORKIDLE);
    }

    public void takeScreenshot(String filename) {
        page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get(filename)));
    }
}
