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
    private final Locator searchButton = page.locator("button[type='submit']");
    private final Locator htmlLink = page.locator("a:has-text('HTML')");

    public void enterSearchText(String text) {
        searchInput.fill(text);
    }

    public void clickSearchButton() {
        searchButton.click();
    }

    public void clickHtmlLink() {
        htmlLink.click();
    }
}