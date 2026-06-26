package com.automation.pages;

import com.automation.base.BaseTestManager;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class WikipediaArticlePage {
    private Page page;

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
        page.locator("//a[contains(text(), '" + articleTitle + "')]").first().click();
    }

    public void verifyArticlePageLoaded(String expectedTitle) {
        page.waitForLoadState(LoadState.NETWORKIDLE);
        Locator articleTitleLocator = page.locator("//h1[@id='firstHeading']");
        assertThat(articleTitleLocator).isVisible();
        assertThat(articleTitleLocator).hasText(expectedTitle);
    }

    public void takeScreenshot(String filename) {
        page.screenshot(new com.microsoft.playwright.Page.ScreenshotOptions().setPath(java.nio.file.Paths.get(filename)));
    }
}