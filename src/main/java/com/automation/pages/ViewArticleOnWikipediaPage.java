package com.automation.pages;

import com.automation.base.BaseTestManager;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;

public class ViewArticleOnWikipediaPage {
    private final Page page;
    private final Locator searchField;
    private final Locator searchButton;
    private final Locator errorMessage;
    private final Locator noResultsMessage;

    public ViewArticleOnWikipediaPage(BaseTestManager testManager) {
        this.page = testManager.getPage();
        this.searchField = page.locator("#searchInput");
        this.searchButton = page.locator("button[type='submit']");
        this.errorMessage = page.locator(".mw-search-errorbox-caption");
        this.noResultsMessage = page.locator(".mw-search-nonefound");
    }

    public void navigateToWikipediaHomepage(String url) {
        page.navigate(url);
    }

    public void searchForTerm(String term) {
        System.out.println("\ud83d\udccd Entering search term: " + term);
        searchField.fill(term);
    }

    public void clickSearchButton() {
        System.out.println("\ud83d\udccd Clicking 'Search' button");
        searchButton.click();
        page.waitForLoadState(LoadState.NETWORKIDLE);
    }

    public void clickSearchResultLink(String selector) {
        System.out.println("\ud83d\udccd Clicking search result link: " + selector);
        page.locator(selector).first().click();
        page.waitForLoadState(LoadState.NETWORKIDLE);
    }

    public boolean verifyErrorMessageDisplayed(String expectedMessage) {
        String actualMessage = errorMessage.textContent();
        System.out.println("\ud83d\udccd Verifying error message: " + expectedMessage);
        return actualMessage.contains(expectedMessage);
    }

    public boolean verifyNoResultsDisplayed() {
        System.out.println("\ud83d\udccd Verifying no results message is displayed");
        return noResultsMessage.isVisible();
    }

    public void takeScreenshot(String fileName) {
        page.screenshot(new com.microsoft.playwright.options.ScreenshotOptions().setPath(fileName));
    }
}