package com.automation.pages;

import com.automation.base.BaseTestManager;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;

public class ViewWikipediaArticlePage {

    private final BaseTestManager testManager;
    private final Locator searchField;
    private final Locator searchButton;
    private final Locator topSearchResult;

    public ViewWikipediaArticlePage(BaseTestManager testManager) {
        this.testManager = testManager;
        this.searchField = testManager.getPage().locator("#searchInput");
        this.searchButton = testManager.getPage().locator("button[type='submit']");
        this.topSearchResult = testManager.getPage().locator("a.mw-searchSuggest-link").first();
    }

    public void navigateToUrl(String url) {
        testManager.getPage().navigate(url);
    }

    public void fillSearchField(String query) {
        searchField.fill(query);
    }

    public void clickSearchButton() {
        searchButton.click();
    }

    public void waitForSearchResults() {
        Page page = testManager.getPage();
        page.waitForSelector("a.mw-searchSuggest-link");
    }

    public void clickTopSearchResult() {
        topSearchResult.click();
    }

    public void waitForArticleLoad() {
        Page page = testManager.getPage();
        page.waitForLoadState(LoadState.DOMCONTENTLOADED);
    }

    public void waitForNoResults() {
        Page page = testManager.getPage();
        page.waitForSelector(".searchmenu-header");
    }

    public void takeScreenshot(String fileName) {
        testManager.getPage().screenshot(new Page.ScreenshotOptions().setPath(fileName));
    }
}