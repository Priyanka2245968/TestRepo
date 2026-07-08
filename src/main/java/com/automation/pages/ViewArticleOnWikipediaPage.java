package com.automation.pages;

import com.automation.base.BaseTestManager;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.options.LoadState;
import com.microsoft.playwright.options.WaitForSelectorState;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class ViewArticleOnWikipediaPage {
    private final BaseTestManager testManager;
    private final Locator searchInput;
    private final Locator searchButton;
    private final Locator searchResultsContainer;
    private final Locator articleContent;
    private final Locator noResultsMessage;

    public ViewArticleOnWikipediaPage(BaseTestManager testManager) {
        this.testManager = testManager;
        this.searchInput = testManager.getPage().locator("#searchInput");
        this.searchButton = testManager.getPage().locator("button[type='submit']");
        this.searchResultsContainer = testManager.getPage().locator(".mw-search-results");
        this.articleContent = testManager.getPage().locator(".mw-parser-output");
        this.noResultsMessage = testManager.getPage().locator(".mw-search-nonefound");
    }

    public void navigateToWikipedia() {
        testManager.getPage().navigate("https://www.wikipedia.org/");
        testManager.getPage().waitForLoadState(LoadState.NETWORKIDLE);
    }

    public void navigateToArticle(String url) {
        testManager.getPage().navigate(url);
        testManager.getPage().waitForLoadState(LoadState.NETWORKIDLE);
    }

    public void searchForArticle(String searchTerm) {
        searchInput.fill(searchTerm);
    }

    public void clickSearchButton() {
        searchButton.click();
    }

    public void waitForSearchResults() {
        searchResultsContainer.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
    }

    public void clickArticleLink(String articleTitle) {
        Locator articleLink = searchResultsContainer.locator("a").filter(new Locator.FilterOptions().setHasText(articleTitle)).first();
        articleLink.click();
        testManager.getPage().waitForLoadState(LoadState.NETWORKIDLE);
    }

    public void waitForArticleLoad() {
        articleContent.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
    }

    public void verifyArticleStructure() {
        assertThat(articleContent).isVisible();
    }

    public void waitForNoResultsMessage() {
        noResultsMessage.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
    }

    public void takeScreenshot(String filename) {
        testManager.getPage().screenshot(new com.microsoft.playwright.Page.ScreenshotOptions().setPath(java.nio.file.Paths.get(filename)));
    }
}