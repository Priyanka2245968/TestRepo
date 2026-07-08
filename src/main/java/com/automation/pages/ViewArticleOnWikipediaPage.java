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
    private final Locator searchResultsContainer;
    private final Locator articleContent;

    public ViewArticleOnWikipediaPage(BaseTestManager testManager) {
        this.testManager = testManager;
        this.searchInput = testManager.getPage().locator("#searchInput");
        this.searchButton = testManager.getPage().locator("button[type='submit']");
        this.articleLink = testManager.getPage().locator(".mw-search-results a");
        this.searchResultsContainer = testManager.getPage().locator(".mw-search-results");
        this.articleContent = testManager.getPage().locator(".mw-parser-output");
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
        testManager.getPage().waitForSelector(searchResultsContainer, new Page.WaitForSelectorOptions().setState(WaitForSelectorState.VISIBLE));
    }

    public void clickArticleLink(String articleTitle) {
        articleLink.filter(new Locator.FilterOptions().setHasText(articleTitle)).first().click();
    }

    public void waitForArticleLoad() {
        testManager.getPage().waitForSelector(articleContent, new Page.WaitForSelectorOptions().setState(WaitForSelectorState.VISIBLE));
    }

    public void verifyArticleStructure() {
        // Implement assertions to verify the structure of the article page
        // For example, check for the presence of specific sections or elements
        assertThat(articleContent).isVisible();
    }

    public void takeScreenshot(String filename) {
        testManager.getPage().screenshot(new Page.ScreenshotOptions().setPath(testManager.getScreenshotPath(filename)));
    }
}
