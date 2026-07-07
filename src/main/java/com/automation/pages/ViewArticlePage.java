package com.automation.pages;

import com.automation.base.BaseTestManager;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;

import java.nio.file.Paths;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.testng.Assert.assertTrue;

public class ViewArticlePage {
    private final Page page;
    private final Locator searchInput;
    private final Locator searchButton;

    public ViewArticlePage(BaseTestManager testManager) {
        this.page = testManager.getPage();
        this.searchInput = page.locator("#searchInput");
        this.searchButton = page.locator("button[type='submit']");
    }

    public void navigateToWikipedia() {
        page.navigate("https://www.wikipedia.org/");
    }

    public void searchForArticle(String searchTerm) {
        searchInput.fill(searchTerm);
    }

    public void clickSearchButton() {
        searchButton.click();
    }

    public void verifySearchResultsLoaded() {
        page.waitForLoadState(LoadState.NETWORKIDLE);
        assertTrue(page.locator(".mw-search-results").isVisible());
    }

    public void clickArticleLink(String articleName) {
        Locator articleLink = page.locator("a[href='/wiki/" + articleName + "']");
        articleLink.waitFor(new Locator.WaitForOptions().setState(com.microsoft.playwright.options.WaitForSelectorState.VISIBLE));
        articleLink.click();
    }

    public void verifyArticleLoaded(String articleName) {
        page.waitForLoadState(LoadState.NETWORKIDLE);
        assertThat(page).hasTitle(articleName + " - Wikipedia");
    }

    public void verifyNoSearchResults() {
        page.waitForLoadState(LoadState.NETWORKIDLE);
        assertTrue(page.locator(".mw-search-results").isHidden());
    }

    public void takeScreenshot(String filename) {
        page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get(filename)));
    }
}
