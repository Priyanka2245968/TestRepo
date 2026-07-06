package com.automation.pages;

import com.automation.base.BaseTestManager;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.options.LoadState;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.testng.Assert.assertTrue;

public class ViewArticleOnWikipediaPage {

    private final BaseTestManager testManager;
    private final Locator searchInput;
    private final Locator searchButton;
    private final Locator pythonProgrammingLanguageLink;

    public ViewArticleOnWikipediaPage(BaseTestManager testManager) {
        this.testManager = testManager;
        this.searchInput = testManager.getPage().locator("#searchInput");
        this.searchButton = testManager.getPage().locator("button[type='submit']");
        this.pythonProgrammingLanguageLink = testManager.getPage().locator("a[href='/wiki/Python_(programming_language)']");
    }

    public void navigateToWikipediaHomepage() {
        testManager.getPage().navigate("https://www.wikipedia.org/");
    }

    public void searchForTerm(String term) {
        searchInput.fill(term);
    }

    public void clickSearchButton() {
        searchButton.click();
    }

    public void waitForSearchResultsToLoad() {
        testManager.getPage().waitForLoadState(LoadState.NETWORKIDLE);
    }

    public void clickPythonProgrammingLanguageLink() {
        pythonProgrammingLanguageLink.click();
    }

    public void waitForArticleToLoad() {
        testManager.getPage().waitForLoadState(LoadState.NETWORKIDLE);
    }

    public void verifyArticleContentVisible() {
        Locator articleTitle = testManager.getPage().locator("h1");
        assertThat(articleTitle).containsText("Python (programming language)");
        Locator articleContent = testManager.getPage().locator(".mw-parser-output");
        assertThat(articleContent).isVisible();
    }

    public void waitForNoResultsPage() {
        testManager.getPage().waitForLoadState(LoadState.NETWORKIDLE);
    }

    public void verifyNoResultsPageVisible() {
        Locator noResultsMessage = testManager.getPage().locator(".mw-search-nonefound");
        assertThat(noResultsMessage).isVisible();
    }

    public void waitForErrorMessageToLoad() {
        testManager.getPage().waitForLoadState(LoadState.NETWORKIDLE);
    }

    public void verifyErrorMessageVisible() {
        Locator errorMessage = testManager.getPage().locator(".mw-search-error");
        assertTrue(errorMessage.textContent().contains("Search request is longer than the maximum allowed length"));
    }

    public void verifyNoResultsMessage() {
        Locator noResultsMessage = testManager.getPage().locator(".mw-search-nonefound");
        assertThat(noResultsMessage).isVisible();
    }

    public void verifyNoArticleLinksDisplayed() {
        Locator articleLinks = testManager.getPage().locator(".mw-search-results a");
        assertTrue(articleLinks.count() == 0, "Article links should not be displayed");
    }

    public void takeScreenshot(String filename) {
        testManager.getPage().screenshot(new com.microsoft.playwright.Page.ScreenshotOptions().setPath(java.nio.file.Paths.get(filename)));
    }
}
