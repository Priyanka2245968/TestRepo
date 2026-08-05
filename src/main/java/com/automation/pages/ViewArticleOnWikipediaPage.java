package com.automation.pages;

import com.automation.base.BaseTestManager;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class ViewArticleOnWikipediaPage {

    private final Page page;
    private final Locator searchInput;
    private final Locator searchButton;

    public ViewArticleOnWikipediaPage(BaseTestManager testManager) {
        this.page = testManager.getPage();
        this.searchInput = page.locator("#searchInput");
        this.searchButton = page.locator("button[type='submit']");
    }

    public void navigateToWikipediaHomepage() {
        page.navigate("https://www.wikipedia.org/");
    }

    public void searchForTerm(String term) {
        searchInput.fill(term);
    }

    public void clickSearchButton() {
        searchButton.click();
    }

    public void waitForSearchResultsPage() {
        page.waitForLoadState(LoadState.NETWORKIDLE);
    }

    public void clickFirstSearchResult() {
        page.locator("a[href='/wiki/Main_Page']").first().click();
        page.waitForLoadState(LoadState.NETWORKIDLE);
    }

    public void waitForArticlePage() {
        page.waitForLoadState(LoadState.NETWORKIDLE);
    }

    public void takeScreenshot(String filename) {
        page.screenshot(new Page.ScreenshotOptions().setPath(java.nio.file.Paths.get(filename)));
    }

    public void verifyHomepageContentAccessible() {
        assertThat(page.locator("a[href='/wiki/Main_Page']")).isVisible();
        assertThat(page.locator("a[href='/wiki/Wikipedia:Contents']")).isVisible();
        assertThat(page.locator("a[href='/wiki/Portal:Current_events']")).isVisible();
    }
}