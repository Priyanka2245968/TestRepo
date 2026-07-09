package com.automation.pages;

import com.automation.base.BaseTestManager;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class ViewArticlePage {
    private final Page page;
    private final Locator searchInput;
    private final Locator searchButton;
    private final Locator htmlArticleLink;

    public ViewArticlePage(BaseTestManager testManager) {
        this.page = testManager.getPage();
        this.searchInput = page.locator("#searchInput");
        this.searchButton = page.locator("button[type='submit']");
        this.htmlArticleLink = page.locator("a[href='/wiki/HTML']");
    }

    public void navigateToWikipedia() {
        page.navigate("https://www.wikipedia.org");
    }

    public void searchForArticle(String searchText) {
        searchInput.fill(searchText);
    }

    public void clickSearchButton() {
        searchButton.click();
    }

    public void clickArticleLink(String linkText) {
        page.locator("a:has-text('" + linkText + "')").click();
    }

    public void takeScreenshot(String filename) {
        page.screenshot(new Page.ScreenshotOptions().setPath(java.nio.file.Paths.get(filename)));
    }
}
