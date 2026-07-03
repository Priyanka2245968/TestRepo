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
    private final Locator loginLink;
    private final Locator createAccountLink;
    private final Locator createAccountPageHeading;

    public ViewArticleOnWikipediaPage(BaseTestManager testManager) {
        this.page = testManager.getPage();
        this.searchInput = page.locator("input[name='search']");
        this.searchButton = page.locator("button[type='submit']");
        this.pythonProgrammingLanguageLink = page.locator("a:has-text('Python (programming language)')");
        this.loginLink = page.locator("a:has-text('Log in')");
        this.createAccountLink = page.locator("a:has-text('Create account')");
        this.createAccountPageHeading = page.locator("h1:has-text('Create account')");
    }

    public void navigateToWikipedia() {
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
        page.locator("h1:has-text('Search results')").isVisible();
    }

    public void clickPythonProgrammingLanguageLink() {
        pythonProgrammingLanguageLink.click();
        page.waitForLoadState(LoadState.NETWORKIDLE);
    }

    public void verifyArticlePageLoaded() {
        page.waitForLoadState(LoadState.NETWORKIDLE);
        page.locator("h1:has-text('Python (programming language)')").isVisible();
    }

    public void takeScreenshot(String fileName) {
        page.screenshot(new Page.ScreenshotOptions().setPath(java.nio.file.Paths.get(fileName)));
    }

    public boolean verifyLoginAndCreateAccountLinksVisible() {
        return loginLink.isVisible() && createAccountLink.isVisible();
    }

    public void clickCreateAccountLink() {
        createAccountLink.click();
        page.waitForLoadState(LoadState.NETWORKIDLE);
    }

    public boolean verifyCreateAccountPageLoaded() {
        return createAccountPageHeading.isVisible();
    }

    public void clickSearchButton() {
        searchButton.click();
        page.waitForLoadState(LoadState.NETWORKIDLE);
    }

    public boolean verifyNoSearchResultsDisplayed() {
        return !page.locator("div.results").isVisible();
    }
}