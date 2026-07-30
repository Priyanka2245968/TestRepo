package com.automation.pages;

import com.automation.base.BaseTestManager;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.options.LoadState;

public class WikipediaArticleViewPage {
    private final com.microsoft.playwright.Page page;
    private final Locator searchInput;
    private final Locator searchButton;
    private final Locator articleTitle;
    private final Locator searchResultsContainer;
    private final Locator onThisDayLink;

    public WikipediaArticleViewPage(BaseTestManager testManager) {
        this.page = testManager.getPage();
        this.searchInput = page.locator("#searchInput");
        this.searchButton = page.locator("button[type='submit']");
        this.articleTitle = page.locator(".mw-body-content h1");
        this.searchResultsContainer = page.locator(".mw-search-results");
        this.onThisDayLink = page.locator("a[href='/wiki/Wikipedia:On_this_day']");
    }

    public void navigateToWikipedia() {
        page.navigate("https://www.wikipedia.org/");
    }

    public void searchForTerm(String term) {
        searchInput.fill(term);
    }

    public void clickSearchButton() {
        searchButton.click();
    }

    public void waitForSearchResults() {
        page.waitForLoadState(LoadState.NETWORKIDLE);
    }

    public void clickTopSearchResult() {
        searchResultsContainer.locator("a").first().click();
    }

    public String getArticleTitle() {
        return articleTitle.textContent();
    }

    public void clickOnThisDayLink() {
        onThisDayLink.click();
    }

    public void waitForOnThisDayPage() {
        page.waitForLoadState(LoadState.NETWORKIDLE);
    }

    public String getPageTitle() {
        return page.title();
    }

    public Locator getSearchResultsContainer() {
        return searchResultsContainer;
    }

    public void takeScreenshot(String filename) {
        page.screenshot(new com.microsoft.playwright.Page.ScreenshotOptions().setPath(java.nio.file.Paths.get(filename)));
    }
}