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

    public void searchForArticle(String searchTerm) {
        page.locator("#searchInput").fill(searchTerm);
        page.locator("button[type='submit']").click();
        page.waitForLoadState(LoadState.NETWORKIDLE);
    }

    public void openArticle(String articleTitle) {
        page.locator("#vector-main-menu-dropdown-checkbox").click();
        page.waitForLoadState(LoadState.NETWORKIDLE);
    }

    public void verifyArticleDisplayed(String articleTitle) {
        assertThat(page).hasTitle(articleTitle);
    }

    public void takeScreenshot(String filename) {
        page.screenshot(new Page.ScreenshotOptions().setPath(java.nio.file.Paths.get(filename)));
    }
}