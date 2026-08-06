package com.automation.pages;

import com.automation.base.BaseTestManager;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;

import java.nio.file.Paths;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.testng.Assert.assertTrue;

public class ViewArticleOnWikipediaPage {

    public final Page page;
    private final Locator searchInput;
    private final Locator searchButton;
    private final Locator htmlLink;

    public ViewArticleOnWikipediaPage(BaseTestManager testManager) {
        this.page = testManager.getPage();
        this.searchInput = page.locator("#searchInput");
        this.searchButton = page.locator("button[type='submit']");
        this.htmlLink = page.locator("a[href='/wiki/HTML']");
    }

    public void navigateToWikipedia() {
        page.navigate("https://www.wikipedia.org/");
    }

    public void fillSearchField(String text) {
        System.out.println("📍 Fill 'Search Wikipedia' field with: " + text);
        searchInput.fill(text);
    }

    public void clickSearchButton() {
        System.out.println("📍 Click 'Search' icon button");
        searchButton.click();
        page.waitForLoadState(LoadState.NETWORKIDLE);
    }

    public void clickHtmlLinkInSearchResults() {
        System.out.println("📍 Click 'HTML' link in the search results");
        htmlLink.first().click();
        page.waitForLoadState(LoadState.NETWORKIDLE);
    }

    public void verifyOverlengthSearchError() {
        System.out.println("📍 Verify over-length search error message");
        page.waitForLoadState(LoadState.NETWORKIDLE);
        assertThat(page.locator("text=An error has occurred while searching: Search request is longer than the maximum allowed length")).isVisible();
    }

    public void verifyNoResultsFound() {
        System.out.println("📍 Verify no results found message");
        page.waitForLoadState(LoadState.NETWORKIDLE);
        assertThat(page.locator("text=There were no results matching the query")).isVisible();
    }

    public void takeScreenshot(String filename) {
        System.out.println("📸 Taking screenshot: " + filename);
        page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get(filename)));
    }
}