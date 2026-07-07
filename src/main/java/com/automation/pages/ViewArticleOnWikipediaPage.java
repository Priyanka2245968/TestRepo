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
    private final Locator pythonArticleLink;

    public ViewArticleOnWikipediaPage(BaseTestManager testManager) {
        this.testManager = testManager;
        this.searchInput = testManager.getPage().locator("#searchInput");
        this.searchButton = testManager.getPage().locator("button[type='submit']");
        this.pythonArticleLink = testManager.getPage().locator("#vector-main-menu-dropdown-checkbox");
    }

    public void navigateToWikipedia() {
        testManager.getPage().navigate("https://www.wikipedia.org/");
    }

    public void searchForTopic(String topic) {
        searchInput.fill(topic);
    }

    public void clickSearchButton() {
        searchButton.click();
    }

    public void waitForSearchResults() {
        testManager.getPage().waitForLoadState(LoadState.NETWORKIDLE);
    }

    public void clickPythonProgrammingLanguageLink() {
        pythonArticleLink.click();
    }

    public void verifyPythonArticlePageLoaded() {
        testManager.getPage().waitForLoadState(LoadState.NETWORKIDLE);
        assertThat(testManager.getPage()).hasTitle("Python (programming language) - Wikipedia");
    }

    public void verifyArticleContentReadable() {
        Locator articleContent = testManager.getPage().locator(".mw-parser-output");
        assertThat(articleContent).isVisible();
        assertTrue(articleContent.textContent().length() > 0);
    }

    public void waitForBlankSearchResults() {
        testManager.getPage().waitForLoadState(LoadState.NETWORKIDLE);
    }

    public void verifyBlankSearchResultsMessage() {
        Locator blankResultsMessage = testManager.getPage().locator(".mw-search-nonefound");
        assertThat(blankResultsMessage).isVisible();
        assertTrue(blankResultsMessage.textContent().contains("No results found. Please try another search."));
    }

    public void takeScreenshot(String filename) {
        testManager.getPage().screenshot(new com.microsoft.playwright.Page.ScreenshotOptions().setPath(java.nio.file.Paths.get(filename)));
    }
}