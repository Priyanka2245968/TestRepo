package com.automation.pages;

import com.automation.base.BaseTestManager;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;

public class WikipediaSearchPage {
    private final Page page;
    private final Locator searchInput;
    private final Locator searchButton;
    private final Locator articleTitleLocator;

    public WikipediaSearchPage(BaseTestManager testManager) {
        this.page = testManager.getPage();
        this.searchInput = page.locator("#searchInput");
        this.searchButton = page.locator("button[type='submit']");
        this.articleTitleLocator = page.locator("h1");
    }

    public void navigateToWikipedia() {
        page.navigate("https://www.wikipedia.org/");
        page.waitForLoadState(LoadState.NETWORKIDLE);
    }

    public void searchForTopic(String topic) {
        searchInput.fill(topic);
        searchButton.click();
    }

    public void waitForSearchResults() {
        page.waitForLoadState(LoadState.NETWORKIDLE);
    }

    public void clickSearchResult(String resultLink) {
        page.locator("a:has-text(\\"" + resultLink + "\\")").first().click();
    }

    public void waitForArticleLoad() {
        page.waitForLoadState(LoadState.NETWORKIDLE);
    }

    public Locator getArticleTitleLocator() {
        return articleTitleLocator;
    }

    public String getNoResultsMessage() {
        return page.locator(".mw-search-nonefound").first().textContent();
    }

    public String getErrorMessage() {
        return page.locator(".mw-search-error").first().textContent();
    }

    public String getSearchInputValue() {
        return searchInput.inputValue();
    }

    public void takeScreenshot(String filename) {
        page.screenshot(new Page.ScreenshotOptions().setPath(java.nio.file.Paths.get(filename)));
    }
}