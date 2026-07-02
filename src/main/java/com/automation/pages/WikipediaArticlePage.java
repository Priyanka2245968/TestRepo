package com.automation.pages;

import com.automation.base.BaseTestManager;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class WikipediaArticlePage {
    private Page page;
    private Locator searchInput;
    private Locator searchButton;
    private Locator htmlTableLink;

    public WikipediaArticlePage(BaseTestManager testManager) {
        this.page = testManager.getPage();
        this.searchInput = page.locator("input[name='search']");
        this.searchButton = page.locator("button[type='submit'][data-testid='search-button']");
        this.htmlTableLink = page.locator("a[href='/wiki/HTML_table']");
    }

    public void searchWikipedia(String query) {
        searchInput.fill(query);
        searchButton.click();
    }

    public void clickHtmlTableLink() {
        htmlTableLink.click();
    }
}