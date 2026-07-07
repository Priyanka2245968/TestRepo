package com.automation.pages;

import com.automation.base.BaseTestManager;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class ViewArticlePage {
    private final Page page;
    private final Locator searchInput;
    private final Locator searchButton;
    private final Locator articleLink;
    private final Locator searchResultsContainer;

    public ViewArticlePage(BaseTestManager testManager) {
        this.page = testManager.getPage();
        this.searchInput = page.locator("input[name='search']");
        this.searchButton = page.locator("button[type='submit']");
        this.articleLink = page.locator(".mw-search-result-heading a");
        this.searchResultsContainer = page.locator(".mw-search-results");
    }

    public void navigateToWikipedia() {
        page.navigate("https://www.wikipedia.org/");
    }

    public void searchForArticle(String searchTerm) {
        searchInput.fill(searchTerm);
    }

    public void clickSearchButton() {
        searchButton.click();
    }

    public void clickArticleLink(String articleTitle) {
        articleLink.filter(new Locator.FilterOptions().setHasText(articleTitle)).first().click();
    }

    public Locator getSearchResultsContainer() {
        return searchResultsContainer;
    }

    public void takeScreenshot(String filename) {
        page.screenshot(new Page.ScreenshotOptions().setPath(java.nio.file.Paths.get(filename)));
    }
}
