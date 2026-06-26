package com.automation.pages;

import com.automation.base.BaseTestManager;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class WikipediaArticlePage {
    private final Page page;

    public WikipediaArticlePage(BaseTestManager testManager) {
        this.page = testManager.getPage();
    }

    public void navigateToWikipedia() {
        page.navigate("https://www.wikipedia.org/");
        page.waitForLoadState(LoadState.NETWORKIDLE);
    }

    public void searchForTerm(String term) {
        page.locator("#searchInput").fill(term);
        page.locator("#searchButton").click();
        page.waitForLoadState(LoadState.NETWORKIDLE);
    }

    public void clickTopSearchResult() {
        page.locator(".mw-search-results a").first().click();
        page.waitForLoadState(LoadState.NETWORKIDLE);
    }

    public void verifyArticlePageLoaded() {
        assertThat(page.locator("#content")).isVisible();
    }

    public void takeScreenshot(String filename) {
        page.screenshot(new com.microsoft.playwright.Page.ScreenshotOptions().setPath(java.nio.file.Paths.get(filename)));
    }
}