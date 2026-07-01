package com.automation.pages;

import com.automation.base.BaseTestManager;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;

public class WikipediaArticlePage {
    private final Page page;
    private final Locator searchInput, searchButton, articleLink;

    public WikipediaArticlePage(BaseTestManager testManager) {
        this.page = testManager.getPage();
        this.searchInput = page.locator("input[name='search']");
        this.searchButton = page.locator("button[type='submit']");
        this.articleLink = page.locator("#ca-view");
    }

    public void navigateToWikipedia() {
        page.navigate("https://www.wikipedia.org/");
        page.waitForLoadState(LoadState.NETWORKIDLE);
    }

    public void searchForArticle(String query) {
        searchInput.fill(query);
        searchButton.click();
        page.waitForLoadState(LoadState.NETWORKIDLE);
    }

    public void openArticle() {
        articleLink.click();
        page.waitForLoadState(LoadState.NETWORKIDLE);
    }

    public boolean isArticleDisplayed(String title) {
        return page.locator("h1").textContent().contains(title);
    }

    public void takeScreenshot(String filename) {
        page.screenshot(new Page.ScreenshotOptions().setPath(java.nio.file.Paths.get(filename)));
    }
}