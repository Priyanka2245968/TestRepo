package com.automation.pages;

import com.automation.base.BaseTestManager;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class WikipediaArticlePage {
    private final Page page;

    public WikipediaArticlePage(BaseTestManager testManager) {
        this.page = testManager.getPage();
    }

    private final Locator searchInput = page.locator("input[name='search']");
    private final Locator searchButton = page.locator("button[type='submit'][data-testid='search-button']");
    private final Locator htmlTableLink = page.locator("a[href='/wiki/HTML_table']");

    public void searchWikipedia(String query) {
        searchInput.fill(query);
        searchButton.click();
    }

    public void clickHtmlTableLink() {
        htmlTableLink.click();
    }
}