package com.automation.pages;

import com.automation.base.BaseTestManager;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;

public class WikipediaSearchPage {
    private final Page page;

    public WikipediaSearchPage(BaseTestManager testManager) {
        this.page = testManager.getPage();
    }

    private final Locator searchInput = page.locator("#searchInput");
    private final Locator searchButton = page.locator("button[type='submit']");
    private final Locator pythonLink = page.locator("#vector-main-menu-dropdown-checkbox");

    public void navigateToWikipedia() {
        page.navigate("https://www.wikipedia.org");
        page.waitForLoadState(LoadState.NETWORKIDLE);
    }

    public void searchForTerm(String term) {
        searchInput.fill(term);
        searchButton.click();
    }

    public void clickPythonLink() {
        pythonLink.click();
    }
}