package com.automation.pages;

import com.automation.base.BaseTestManager;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;

public class WikipediaArticlePage {
    private static final String WIKIPEDIA_URL = "https://www.wikipedia.org/";
    private final Page page;
    private final Locator searchInput;
    private final Locator searchButton;
    private final Locator firstSearchResult;
    private final Locator articleContent;
    private final Locator noSearchResults;

    public WikipediaArticlePage(BaseTestManager testManager) {
        this.page = testManager.getPage();
        this.searchInput = page.locator("#searchInput");
        this.searchButton = page.locator("button[type='submit']");
        this.firstSearchResult = page.locator("a.mw-searchSuggest-link");
        this.articleContent = page.locator("#mw-content-text");
        this.noSearchResults = page.locator("p.mw-search-nonefound");
    }

    public void navigateToWikipedia() {
        page.navigate(WIKIPEDIA_URL);
    }

    public void searchForArticle(String query) {
        searchInput.fill(query);
        searchButton.click();
        page.waitForLoadState(LoadState.NETWORKIDLE);
    }

    public void openFirstSearchResult() {
        firstSearchResult.click();
        page.waitForLoadState(LoadState.NETWORKIDLE);
    }

    public boolean isArticlePageLoaded() {
        return articleContent.isVisible();
    }

    public boolean isWikipediaHomePageLoaded() {
        return page.url().equals(WIKIPEDIA_URL);
    }

    public boolean isNoSearchResultsDisplayed() {
        return noSearchResults.isVisible();
    }

    public void takeScreenshot(String filename) {
        page.screenshot(new Page.ScreenshotOptions().setPath(java.nio.file.Paths.get(filename)));
    }
}