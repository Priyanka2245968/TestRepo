package com.automation.pages;

import com.automation.base.BaseTestManager;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;

public class WikipediaSearchPage {
    private Page page;

    public WikipediaSearchPage(BaseTestManager testManager) {
        this.page = testManager.getPage();
    }

    private Locator searchInput;
    private Locator searchButton;
    private Locator pythonLink;

    public void navigateToWikipedia() {
        page.navigate("https://www.wikipedia.org");
        page.waitForLoadState(LoadState.NETWORKIDLE);
        searchInput = page.locator("#searchInput");
        searchButton = page.locator("#searchButton");
        pythonLink = page.locator("#vector-main-menu-dropdown-checkbox");
    }

    public void searchForTerm(String term) {
        searchInput.fill(term);
        searchButton.click();
    }

    public void clickPythonLink() {
        pythonLink.click();
    }
}