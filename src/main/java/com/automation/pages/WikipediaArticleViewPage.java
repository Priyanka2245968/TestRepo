package com.automation.pages;

import com.automation.base.BaseTestManager;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;

public class WikipediaArticleViewPage {
    private final Page page;
    private final Locator searchInput;
    private final Locator searchButton;
    private final Locator pythonProgrammingLanguageLink;
    private final Locator randomArticleLink;
    private final Locator noResultsMessage;

    public WikipediaArticleViewPage(BaseTestManager testManager) {
        this.page = testManager.getPage();
        this.searchInput = page.locator("#searchInput");
        this.searchButton = page.locator("button[type='submit']");
        this.pythonProgrammingLanguageLink = page.locator("#vector-main-menu-dropdown-checkbox").first();
        this.randomArticleLink = page.locator("a[href='/wiki/Special:Random']");
        this.noResultsMessage = page.locator("text=There were no results matching the query");
    }

    public void navigateToWikipedia() {
        page.navigate("https://www.wikipedia.org/");
        page.waitForLoadState(LoadState.NETWORKIDLE);
    }

    public void searchForArticle(String searchTerm) {
        searchInput.fill(searchTerm);
    }

    public void clickSearchButton() {
        searchButton.click();
        page.waitForLoadState(LoadState.NETWORKIDLE);
    }

    public void clickPythonProgrammingLanguageLink() {
        pythonProgrammingLanguageLink.click();
        page.waitForLoadState(LoadState.NETWORKIDLE);
    }

    public void clickRandomArticleLink() {
        randomArticleLink.click();
        page.waitForLoadState(LoadState.NETWORKIDLE);
    }

    public String getNoResultsMessage() {
        return noResultsMessage.textContent();
    }

    public void takeScreenshot(String filename) {
        page.screenshot(new Page.ScreenshotOptions().setPath(java.nio.file.Paths.get(filename)));
    }
}