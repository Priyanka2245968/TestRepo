package com.automation.pages;

import com.automation.base.BaseTestManager;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;

public class WikipediaSearchPage {
    private final Page page;
    private final Locator searchButton;
    private final Locator searchField;

    public WikipediaSearchPage(BaseTestManager testManager) {
        this.page = testManager.getPage();
        this.searchButton = page.locator("button[type='submit']");
        this.searchField = page.locator("#ooui-php-1");
    }

    public void navigateToWikipedia() {
        page.navigate("https://www.wikipedia.org");
    }

    public void clickSearchButton() {
        searchButton.click();
    }

    public void enterSearchTerm(String term) {
        searchField.fill(term);
    }

    public String getSearchFieldValue() {
        return searchField.textContent();
    }

    public void takeScreenshot(String filename) {
        page.screenshot(new Page.ScreenshotOptions().setPath(java.nio.file.Paths.get(filename)));
    }
}