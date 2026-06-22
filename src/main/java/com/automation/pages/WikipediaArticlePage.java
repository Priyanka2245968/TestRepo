package com.automation.pages;

import com.automation.base.BaseTestManager;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class WikipediaArticlePage {
    private Page page;

    public WikipediaArticlePage(BaseTestManager testManager) {
        this.page = testManager.getPage();
    }

    private Locator searchInput = page.locator("#searchInput");
    private Locator searchButton = page.locator("button[type='submit']");
    private Locator pythonProgrammingLanguageLink = page.locator("a[title='Python (programming language)']");

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

    public String getPageUrl() {
        return page.url();
    }

    public String getPageContent() {
        return page.textContent();
    }
}