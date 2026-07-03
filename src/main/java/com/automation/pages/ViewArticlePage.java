package com.automation.pages;

import com.automation.base.BaseTestManager;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;

public class ViewArticlePage {
    private final Page page;
    private final Locator searchInput;
    private final Locator searchButton;
    private final Locator articleLink;
    private final Locator errorMessage;

    public ViewArticlePage(BaseTestManager testManager) {
        this.page = testManager.getPage();
        this.searchInput = page.locator("#searchInput");
        this.searchButton = page.locator("button[type='submit']");
        this.articleLink = page.locator(".mw-search-results a");
        this.errorMessage = page.locator(".mw-search-nonefound");
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

    public void clickArticleLink(String linkText) {
        articleLink.filter(new Locator.FilterOptions().setHasText(linkText)).first().click();
    }

    public String getErrorMessage() {
        return errorMessage.textContent();
    }

    public void takeScreenshot(String filename) {
        page.screenshot(new Page.ScreenshotOptions().setPath(java.nio.file.Paths.get(filename)));
    }
}
