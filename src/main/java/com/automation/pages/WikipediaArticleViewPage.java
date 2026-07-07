package com.automation.pages;

import com.automation.base.BaseTestManager;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

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
        this.pythonProgrammingLanguageLink = page.locator("a[href='/wiki/Category:Python_(programming_language)']");
        this.randomArticleLink = page.locator("a[href='/wiki/Special:Random']");
        this.noResultsMessage = page.locator(".mw-search-nonefound");
    }

    public void navigateToWikipedia() {
        page.navigate("https://www.wikipedia.org/");
    }

    public void searchForArticle(String searchTerm) {
        searchInput.fill(searchTerm);
    }

    public void clickSearchButton() {
        searchButton.click();
    }

    public void clickPythonProgrammingLanguageLink() {
        pythonProgrammingLanguageLink.click();
    }

    public void clickRandomArticleLink() {
        randomArticleLink.click();
    }

    public String getNoResultsMessage() {
        return noResultsMessage.textContent();
    }

    public void takeScreenshot(String filename) {
        page.screenshot(new Page.ScreenshotOptions().setPath(java.nio.file.Paths.get(filename)));
    }
}
