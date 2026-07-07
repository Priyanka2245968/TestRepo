package com.automation.pages;

import com.automation.base.BaseTestManager;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import java.nio.file.Paths;

public class ViewArticlePage {
    private final Page page;
    private final Locator searchInput;
    private final Locator searchButton;
    private final Locator articleLink;
    private final Locator searchResultsContainer;

    public ViewArticlePage(BaseTestManager testManager) {
        this.page = testManager.getPage();
        this.searchInput = page.locator("#searchInput");
        this.searchButton = page.locator("button[type='submit']");
        this.articleLink = page.locator("#vector-main-menu-dropdown-checkbox").first();
        this.searchResultsContainer = page.locator(".mw-search-results");
    }

    public void navigateToWikipedia() {
        page.navigate("https://www.wikipedia.org/");
    }

    public String enterVeryLongTextInSearchField() {
        String longText = "A very long string with more than 500 characters...";
        searchInput.fill(longText);
        return longText;
    }

    public String enterInvalidTextInSearchField() {
        String invalidText = "!@#$%^&*()";
        searchInput.fill(invalidText);
        return invalidText;
    }

    public void searchForArticle(String searchTerm) {
        searchInput.fill(searchTerm);
    }

    public void clickSearchButton() {
        searchButton.click();
    }

    public void clickArticleLink(String articleTitle) {
        articleLink.filter(new Locator.FilterOptions().setHasText(articleTitle)).first().click();
    }

    public Locator getSearchResultsContainer() {
        return searchResultsContainer;
    }

    public void takeScreenshot(String filename) {
        page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get(filename)));
    }

    public String verifyErrorMessageForLongSearch() {
        return page.locator(".mw-message-box-error").textContent();
    }

    public String verifyErrorMessageForInvalidSearch() {
        return page.locator(".mw-message-box-error").textContent();
    }
}