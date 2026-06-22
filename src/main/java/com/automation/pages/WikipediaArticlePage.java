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
    private final Locator pythonProgrammingLanguageLink = page.locator("#vector-main-menu-dropdown-checkbox");

    public void searchWikipedia(String query) {
        searchInput.fill(query);
        searchButton.click();
    }

    public void clickPythonProgrammingLanguageLink() {
        pythonProgrammingLanguageLink.click();
    }

    public String getPageTitle() {
        return page.title();
    }
}