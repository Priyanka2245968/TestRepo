package com.automation.pages;

import com.automation.base.BaseTestManager;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.options.LoadState;
import com.microsoft.playwright.options.WaitForSelectorState;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.testng.Assert.assertTrue;

public class ViewArticleOnWikipediaPage {
    private final BaseTestManager testManager;
    private final Locator searchInput;
    private final Locator searchButton;
    private final Locator articleLink;

    public ViewArticleOnWikipediaPage(BaseTestManager testManager) {
        this.testManager = testManager;
        this.searchInput = testManager.getPage().locator("#searchInput");
        this.searchButton = testManager.getPage().locator("button[type='submit']");
        this.articleLink = testManager.getPage().locator(".mw-search-results a");
    }

    public void navigateToWikipedia() {
        testManager.getPage().navigate("https://www.wikipedia.org/");
    }

    public void navigateToArticle(String url) {
        testManager.getPage().navigate(url);
    }

    public void searchForArticle(String searchTerm) {
        searchInput.fill(searchTerm);
    }

    public void clickSearchButton() {
        searchButton.click();
    }

    public void waitForSearchResults() {
        testManager.getPage().waitForLoadState(LoadState.NETWORKIDLE);
    }

    public void clickArticleLink(String articleTitle) {
        articleLink.filter(new Locator.FilterOptions().setHasText(articleTitle)).first().click();
    }

    public void waitForArticleLoad() {
        testManager.getPage().waitForLoadState(LoadState.NETWORKIDLE);
    }

    public void waitForNoResultsMessage() {
        testManager.getPage().locator(".mw-search-nonefound").waitFor();
    }

    public void verifyArticleStructure() {
        Locator articleContent = testManager.getPage().locator("#bodyContent");
        assertThat(articleContent).isVisible();
        assertTrue(articleContent.locator("h2").isVisible());
        assertTrue(articleContent.locator("img").isVisible());
    }

    public void takeScreenshot(String filename) {
        testManager.getPage().screenshot(new com.microsoft.playwright.Page.ScreenshotOptions().setPath(java.nio.file.Paths.get(filename)));
    }
}
