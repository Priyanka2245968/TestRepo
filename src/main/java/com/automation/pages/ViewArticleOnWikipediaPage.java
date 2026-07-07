package com.automation.pages;

import com.automation.base.BaseTestManager;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;

public class ViewArticleOnWikipediaPage {
    private final Page page;
    private final Locator searchInput;
    private final Locator searchIcon;
    private final Locator searchResultsContainer;

    public ViewArticleOnWikipediaPage(BaseTestManager testManager) {
        this.page = testManager.getPage();
        this.searchInput = page.locator("#searchInput");
        this.searchIcon = page.locator("//button[contains(@class,'search')]");
        this.searchResultsContainer = page.locator(".mw-search-results");
    }

    public void navigateToWikipedia() {
        page.navigate("https://www.wikipedia.org/");
    }

    public void searchForArticle(String searchTerm) {
        searchInput.fill(searchTerm);
    }

    public void clickSearchIcon() {
        searchIcon.click();
    }

    public void waitForSearchResults() {
        page.waitForLoadState(LoadState.NETWORKIDLE);
    }

    public void clickArticleLink(String articleTitle) {
        Locator articleLink = searchResultsContainer.locator("a", new Page.GetLocatorOptions().setHasText(articleTitle));
        articleLink.click();
    }

    public String getArticleTitle() {
        return page.locator("#firstHeading").textContent();
    }

    public String getArticleContent() {
        return page.locator(".mw-parser-output").textContent();
    }

    public void takeScreenshot(String fileName) {
        page.screenshot(new Page.ScreenshotOptions().setPath(fileName));
    }
}