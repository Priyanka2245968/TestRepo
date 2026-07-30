package com.automation.pages;

import com.automation.base.BaseTestManager;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;
import java.nio.file.Paths;

public class WikipediaPage {
    private final Page page;
    private final Locator searchInput, searchButton, searchResult;

    public WikipediaPage(BaseTestManager testManager) {
        this.page = testManager.getPage();
        this.searchInput = page.locator("#searchInput");
        this.searchButton = page.locator("button[type='submit']");
        this.searchResult = page.locator("a[href='/wiki/Main_Page']");
    }

    public void navigateToWikipedia() {
        page.navigate("https://www.wikipedia.org/");
    }

    public void searchForTopic(String topic) {
        System.out.println("📍 Entering '" + topic + "' in the 'Search Wikipedia' field");
        searchInput.fill(topic);
    }

    public void clickSearchButton() {
        System.out.println("📍 Clicking the 'Search' button");
        searchButton.click();
        page.waitForLoadState(LoadState.NETWORKIDLE);
    }

    public void clickSearchResult() {
        System.out.println("📍 Clicking the link for the search result");
        searchResult.first().click();
        page.waitForLoadState(LoadState.NETWORKIDLE);
    }

    public void takeScreenshot(String filename) {
        page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get(filename)));
    }
}
