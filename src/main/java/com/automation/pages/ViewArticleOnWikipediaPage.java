package com.automation.pages;

import com.automation.base.BaseTestManager;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;

public class ViewArticleOnWikipediaPage {
    private final Page page;
    private final Locator searchInput;
    private final Locator searchButton;
    private final Locator pythonProgrammingLanguageLink;

    public ViewArticleOnWikipediaPage(BaseTestManager testManager) {
        this.page = testManager.getPage();
        this.searchInput = page.locator("#searchInput");
        this.searchButton = page.locator("button[type='submit']");
        this.pythonProgrammingLanguageLink = page.locator("#vector-main-menu-dropdown-checkbox");
    }

    public void navigateToWikipedia() {
        page.navigate("https://www.wikipedia.org/");
        page.waitForLoadState(LoadState.NETWORKIDLE);
    }

    public void navigateToWikipediaHomepage() {
        page.navigate("https://www.wikipedia.org/");
        page.waitForLoadState(LoadState.NETWORKIDLE);
    }

    public void searchForPythonProgrammingLanguage() {
        searchInput.fill("Python programming language");
        searchButton.click();
        page.waitForLoadState(LoadState.NETWORKIDLE);
    }

    public void verifySearchResultsHeading() {
        page.waitForLoadState(LoadState.NETWORKIDLE);
        page.locator("h1").waitFor(new Locator.WaitForOptions().setTimeout(5000));
        String title = page.locator("h1").textContent();
        assertTrue(title.contains("Python (programming language) - Search results - Wikipedia"));
    }

    public void clickPythonProgrammingLanguageLink() {
        pythonProgrammingLanguageLink.click();
        page.waitForLoadState(LoadState.NETWORKIDLE);
    }

    public void verifyArticlePageLoaded() {
        page.waitForLoadState(LoadState.NETWORKIDLE);
        page.locator("h1").waitFor(new Locator.WaitForOptions().setTimeout(5000));
        String title = page.locator("h1").textContent();
        assertTrue(title.contains("Python (programming language)"));
    }

    public void verifyLoginAndCreateAccountLinksVisible() {
        page.locator("a[href='/w/index.php?title=Special:UserLogin']").waitFor(new Locator.WaitForOptions().setTimeout(5000));
        page.locator("a[href='/w/index.php?title=Special:CreateAccount']").waitFor(new Locator.WaitForOptions().setTimeout(5000));
    }

    public void clickCreateAccountLink() {
        page.locator("a[href='/w/index.php?title=Special:CreateAccount']").click();
        page.waitForLoadState(LoadState.NETWORKIDLE);
    }

    public void verifyCreateAccountPageLoaded() {
        page.waitForLoadState(LoadState.NETWORKIDLE);
        page.locator("h1").waitFor(new Locator.WaitForOptions().setTimeout(5000));
        assertTrue(page.locator("h1").textContent().contains("Create account"));
    }

    public void clickSearchButtonWithoutQuery() {
        searchButton.click();
        page.waitForLoadState(LoadState.NETWORKIDLE);
    }

    public void verifyNoResultsMessageDisplayed() {
        page.waitForLoadState(LoadState.NETWORKIDLE);
        page.locator(".mw-search-nonefound").waitFor(new Locator.WaitForOptions().setTimeout(5000));
        String noResultsMessage = page.locator(".mw-search-nonefound").textContent();
        assertTrue(noResultsMessage.contains("No results found"));
    }

    public void enterLongSearchQuery() {
        String longQuery = "This is a very long search query that exceeds the maximum allowed length of 500 characters. " +
                "It is used to test the behavior of Wikipedia's search functionality when an excessively long query is submitted. " +
                "The purpose of this test is to ensure that the system handles such cases gracefully and provides an appropriate error message to the user.";
        searchInput.fill(longQuery);
    }

    public void enterInvalidSearchQuery(String query) {
        searchInput.fill(query);
    }

    public void clickSearchButton() {
        searchButton.click();
        page.waitForLoadState(LoadState.NETWORKIDLE);
    }

    public String getErrorMessage() {
        page.waitForLoadState(LoadState.NETWORKIDLE);
        page.locator(".mw-search-nonefound").waitFor(new Locator.WaitForOptions().setTimeout(5000));
        return page.locator(".mw-search-nonefound").textContent();
    }

    public void takeScreenshot(String filename) {
        page.screenshot(new Page.ScreenshotOptions().setPath(java.nio.file.Paths.get(filename)));
    }
}
