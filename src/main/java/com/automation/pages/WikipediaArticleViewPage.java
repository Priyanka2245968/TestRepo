package com.automation.pages;

import com.automation.base.BaseTestManager;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class WikipediaArticleViewPage {
    private final Page page;
    private final Locator searchInput;
    private final Locator searchButton;
    private final Locator articleLink;

    public WikipediaArticleViewPage(BaseTestManager testManager) {
        this.page = testManager.getPage();
        this.searchInput = page.locator("#searchInput");
        this.searchButton = page.locator("button[type='submit']");
        this.articleLink = page.locator("a:has-text('HTML')");
    }

    public void navigateToWikipediaHomepage() {
        page.navigate("https://www.wikipedia.org/");
    }

    public void enterSearchText(String text) {
        searchInput.fill(text);
    }

    public void clickSearchButton() {
        searchButton.click();
    }

    public void waitForSearchResultsLoad() {
        page.waitForLoadState(LoadState.NETWORKIDLE);
    }

    public void clickArticleLink(String articleTitle) {
        page.locator(String.format("a:has-text('%s')", articleTitle)).click();
    }

    public void waitForArticleLoad() {
        page.waitForLoadState(LoadState.NETWORKIDLE);
    }

    public void verifyHomepageAccessible() {
        assertThat(searchInput).isVisible();
    }

    public void waitForNoSearchResults() {
        assertThat(page.locator(".no-results")).isVisible();
    }

    public void takeScreenshot(String filename) {
        page.screenshot(new Page.ScreenshotOptions().setPath(filename));
    }
}
