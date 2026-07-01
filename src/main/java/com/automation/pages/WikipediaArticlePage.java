package com.automation.pages;

import com.automation.base.BaseTestManager;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;

import java.nio.file.Paths;

public class WikipediaArticlePage {
    private final Page page;
    private final Locator searchInput, searchButton;
    private final Locator tableOfContentsLocator;

    public WikipediaArticlePage(BaseTestManager testManager) {
        this.page = testManager.getPage();
        this.searchInput = page.locator("#searchInput");
        this.searchButton = page.locator("button[type='submit']");
        this.tableOfContentsLocator = page.locator(".toc");
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

    public boolean isArticlePageLoaded(String articleTitle) {
        return page.url().contains(articleTitle.replaceAll(" ", "_")) && page.locator("h1").textContent().contains(articleTitle);
    }

    public Locator getTableOfContentsLocator() {
        return tableOfContentsLocator;
    }

    public boolean isTableOfContentsVisible() {
        return tableOfContentsLocator.isVisible();
    }

    public void takeScreenshot(String filename) {
        page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get(filename)));
    }
}