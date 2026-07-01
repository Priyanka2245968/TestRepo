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

    private final Locator searchInput = page.locator("[data-test-id='search-input']");
    private final Locator searchButton = page.locator("[data-test-id='search-button']");
    private final Locator articleLink = page.locator("[data-test-id='article-link']");

    public void navigateToWikipedia() {
        page.navigate("https://www.wikipedia.org/");
        page.waitForLoadState(LoadState.NETWORKIDLE);
    }

    public void searchForArticle(String query) {
        searchInput.fill(query);
        searchButton.click();
        page.waitForLoadState(LoadState.NETWORKIDLE);
    }

    public void openArticle() {
        articleLink.click();
        page.waitForLoadState(LoadState.NETWORKIDLE);
    }

    public void verifyArticlePageLoaded(String expectedTitle) {
        String actualTitle = page.title();
        assertThat(actualTitle).isEqualTo(expectedTitle);
    }

    public void takeScreenshot(String filename) {
        page.screenshot(new Page.ScreenshotOptions().setPath(java.nio.file.Paths.get(filename)));
    }
}