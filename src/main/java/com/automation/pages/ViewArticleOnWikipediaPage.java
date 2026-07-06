package com.automation.pages;

import com.automation.base.BaseTestManager;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class ViewArticleOnWikipediaPage {

    private final BaseTestManager testManager;
    private final Page page;
    private final Locator searchInput;
    private final Locator searchButton;
    private final Locator pythonProgrammingLanguageLink;
    private final Locator noResultsMessage;

    public ViewArticleOnWikipediaPage(BaseTestManager testManager) {
        this.testManager = testManager;
        this.page = testManager.getPage();
        this.searchInput = page.locator("#searchInput");
        this.searchButton = page.locator("button[type='submit']");
        this.pythonProgrammingLanguageLink = page.locator("#vector-main-menu-dropdown-checkbox").first();
        this.noResultsMessage = page.locator(".no-results-info");
    }

    public void navigateToWikipediaHomepage() {
        page.navigate("https://www.wikipedia.org/");
    }

    public void searchForTerm(String term) {
        searchInput.fill(term);
    }

    public void clickSearchButton() {
        searchButton.click();
    }

    public void waitForSearchResultsToLoad() {
        page.waitForLoadState(LoadState.NETWORKIDLE);
    }

    public void clickPythonProgrammingLanguageLink() {
        pythonProgrammingLanguageLink.click();
        page.waitForLoadState(LoadState.NETWORKIDLE);
    }

    public void waitForArticleToLoad() {
        page.waitForLoadState(LoadState.NETWORKIDLE);
    }

    public void verifyArticleContentVisible() {
        assertThat(page.locator(".mw-parser-output")).isVisible();
    }

    public void verifyNoResultsPageVisible() {
        assertThat(noResultsMessage).isVisible();
    }

    public void takeScreenshot(String fileName) {
        page.screenshot(new Page.ScreenshotOptions().setPath(java.nio.file.Paths.get(fileName)));
    }
}