package com.automation.pages;

import com.automation.base.BaseTestManager;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;

import java.nio.file.Paths;

public class WikipediaArticlePage {
    private final Page page;
    private final Locator searchInput;
    private final Locator searchButton;
    private final Locator searchResultsContainer;
    private final Locator articleContent;
    private final Locator errorMessage;

    public WikipediaArticlePage(BaseTestManager testManager) {
        this.page = testManager.getPage();
        this.searchInput = page.locator("input[name='search']");
        this.searchButton = page.locator("button[type='submit']");
        this.searchResultsContainer = page.locator(".mw-search-results");
        this.articleContent = page.locator("#bodyContent");
        this.errorMessage = page.locator(".mw-search-errorbox");
    }

    public void navigateToWikipedia() {
        page.navigate("https://www.wikipedia.org/");
        page.waitForLoadState(LoadState.NETWORKIDLE);
    }

    public void searchForTerm(String term) {
        searchInput.fill(term);
        searchButton.click();
    }

    public void clickSearchButton() {
        searchButton.click();
    }

    public void waitForSearchResults() {
        page.waitForLoadState(LoadState.NETWORKIDLE);
    }

    public void clickSearchResult(String resultText) {
        searchResultsContainer.locator("xpath=//*[contains(text(), '" + resultText + "')]").first().click();
    }

    public void waitForArticleLoad() {
        page.waitForLoadState(LoadState.NETWORKIDLE);
    }

    public Locator getArticleContent() {
        return articleContent;
    }

    public void takeScreenshot(String fileName) {
        page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get(fileName)));
    }
}