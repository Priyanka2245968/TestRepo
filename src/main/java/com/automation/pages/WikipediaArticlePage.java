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
    }

    public void searchForArticle(String query) {
        page.locator("input[name='search']").first().fill(query);
        page.locator("button[type='submit']").first().click();
    }

    public void openArticle(String articleTitle) {
        page.locator("//a[contains(@title, '" + articleTitle + "')]").first().click();
    }

    public void verifyArticleLoaded(String expectedTitle) {
        page.waitForLoadState(LoadState.NETWORKIDLE);
        assertThat(page).hasTitle(expectedTitle);
    }

    public void takeScreenshot(String filename) {
        page.screenshot(new Page.ScreenshotOptions().setPath(java.nio.file.Paths.get(filename)));
    }
}