package com.automation.pages;

import com.automation.base.BaseTestManager;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;

import java.nio.file.Paths;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class ViewArticleOnWikipediaPage {

    private final Page page;
    private final Locator searchInput;
    private final Locator searchButton;
    private final Locator htmlLink;
    private final Locator overlengthSearchError;
    private final Locator noResultsFoundMessage;

    public ViewArticleOnWikipediaPage(BaseTestManager testManager) {
        this.page = testManager.getPage();
        this.searchInput = page.locator("#searchInput");
        this.searchButton = page.locator("button[type='submit']");
        this.htmlLink = page.locator("a[href='/wiki/HTML']");
        this.overlengthSearchError = page.locator("text=The search query is too long");
        this.noResultsFoundMessage = page.locator("text=There were no results matching the query");
    }

    public void navigateToWikipedia() {
        page.navigate("https://www.wikipedia.org/");
    }

    public void fillSearchField(String text) {
        System.out.println("\ud83d\udccd Fill 'Search Wikipedia' field with: " + text);
        searchInput.fill(text);
    }

    public void clickSearchButton() {
        System.out.println("\ud83d\udccd Click 'Search' icon button");
        searchButton.click();
        page.waitForLoadState(LoadState.NETWORKIDLE);
    }

    public void clickHtmlLinkInSearchResults() {
        System.out.println("\ud83d\udccd Click 'HTML' link in search results");
        htmlLink.click();
        page.waitForLoadState(LoadState.NETWORKIDLE);
    }

    public Locator getOverlengthSearchError() {
        return overlengthSearchError;
    }

    public Locator getNoResultsFoundMessage() {
        return noResultsFoundMessage;
    }

    public void takeScreenshot(String filename) {
        page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get(filename)));
    }
}