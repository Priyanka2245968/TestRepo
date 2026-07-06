package com.automation.pages;

import com.automation.base.BaseTestManager;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class ViewArticleOnWikipediaPage {

    private final BaseTestManager testManager;
    private final Locator searchInput;
    private final Locator searchButton;
    private final Locator pythonProgrammingLanguageLink;
    private final Locator noResultsMessage;

    public ViewArticleOnWikipediaPage(BaseTestManager testManager) {
        this.testManager = testManager;
        Page page = testManager.getPage();
        this.searchInput = page.locator("input[name='search']");
        this.searchButton = page.locator("button:has-text('Search')");
        this.pythonProgrammingLanguageLink = page.locator("a:has-text('Python (programming language)')");
        this.noResultsMessage = page.locator(".no-results-info");
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
        assertThat(testManager.getPage().locator(".mw-parser-output")).isVisible();
    }

    public void verifyNoResultsPageVisible() {
        assertThat(noResultsMessage).isVisible();
    }

    public void takeScreenshot(String fileName) {
        testManager.getPage().screenshot(new Page.ScreenshotOptions().setPath(java.nio.file.Paths.get(fileName)));
    }
}