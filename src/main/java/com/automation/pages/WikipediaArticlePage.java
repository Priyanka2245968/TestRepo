package com.automation.pages;

import com.automation.base.BaseTestManager;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class WikipediaArticlePage {
    private final Page page;

    public WikipediaArticlePage(BaseTestManager testManager) {
        this.page = testManager.getPage();
    }

    private final Locator searchInput = page.locator("#searchInput");
    private final Locator searchButton = page.locator("button[type='submit']");
    private final Locator htmlTableLink = page.locator("a[href='/wiki/Main_Page']");

    public void searchWikipedia(String query) {
        searchInput.fill(query);
        searchButton.click();
    }

    public void clickHtmlTableLink() {
        htmlTableLink.click();
    }
}