package com.automation.pages;

import com.automation.base.BaseTestManager;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.options.LoadState;
import com.microsoft.playwright.options.WaitForSelectorState;
import java.nio.file.Paths;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.testng.Assert.assertFalse;

public class ViewArticleOnWikipediaPage {
    private final BaseTestManager testManager;
    private final Locator searchInput;
    private final Locator searchButton;
    private final Locator pythonArticleLink;

    public ViewArticleOnWikipediaPage(BaseTestManager testManager) {
        this.testManager = testManager;
        this.searchInput = testManager.getPage().locator("#searchInput");
        this.searchButton = testManager.getPage().locator("button[type='submit']");
        this.pythonArticleLink = testManager.getPage().locator("#vector-main-menu-dropdown-checkbox").first();
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
        assertThat(testManager.getPage()).hasURL("https://en.wikipedia.org/wiki/Python_(programming_language)");
    }

    public void verifyArticleContentReadable() {
        Locator articleContent = testManager.getPage().locator(".mw-parser-output");
        articleContent.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
        assertThat(articleContent).isVisible();
    }

    public void verifyNoSearchResultsShown() {
        Locator noResultsMessage = testManager.getPage().locator(".mw-search-nonefound");
        noResultsMessage.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
        assertThat(noResultsMessage).not().isVisible();
    }

    public void takeScreenshot(String fileName) {
        testManager.getPage().screenshot(new com.microsoft.playwright.Page.ScreenshotOptions().setPath(Paths.get(fileName)));
    }
}