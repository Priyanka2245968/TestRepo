package com.automation.pages;

import com.automation.base.BaseTestManager;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;

public class WikipediaArticlePage {
    private final Page page;
    private final Locator searchInput;
    private final Locator searchButton;
    private final Locator searchResultsContainer;
    private final Locator articleContent;
    private final Locator errorMessage;

    public WikipediaArticlePage(BaseTestManager testManager) {
        this.page = testManager.getPage();
        this.searchInput = page.locator("#searchInput");
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
    }

    public void enterSearchText(String text) {
        searchInput.fill(text);
    }

    public void clickSearchButton() {
        searchButton.click();
    }

    public void waitForSearchResults() {
        page.waitForLoadState(LoadState.NETWORKIDLE);
    }

    public void clickSearchResult(String resultText) {
        Locator resultLink = searchResultsContainer.locator("a", new Locator.LocatorOptions().setHasText(resultText));
        resultLink.click();
    }

    public void waitForArticleLoad() {
        page.waitForLoadState(LoadState.NETWORKIDLE);
    }

    public Locator getArticleContent() {
        return articleContent;
    }

    public Locator getSearchResultsContainer() {
        return searchResultsContainer;
    }

    public String getErrorMessage() {
        return errorMessage.textContent();
    }

    public void takeScreenshot(String filename) {
        page.screenshot(new Page.ScreenshotOptions().setPath(java.nio.file.Paths.get(filename)));
    }
}