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
    private final Locator noSearchResultsContainer;

    public ViewArticleOnWikipediaPage(BaseTestManager testManager) {
        this.page = testManager.getPage();
        this.searchInput = page.locator("#searchInput");
        this.searchButton = page.locator("button[type='submit']");
        this.pythonProgrammingLanguageLink = page.locator("#vector-main-menu-dropdown-checkbox").first();
        this.loginLink = page.locator("a:has-text('Log in')");
        this.createAccountLink = page.locator("a:has-text('Create account')");
        this.createAccountPageHeading = page.locator("h1:has-text('Create account')");
        this.noSearchResultsContainer = page.locator(".results");
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
        page.locator("h1:has-text('Search results')").waitFor(new Locator.WaitForOptions().setTimeout(10000).setState(com.microsoft.playwright.options.WaitForSelectorState.VISIBLE));
    }

    public void clickPythonProgrammingLanguageLink() {
        pythonProgrammingLanguageLink.waitFor(new Locator.WaitForOptions().setState(com.microsoft.playwright.options.WaitForSelectorState.VISIBLE));
        pythonProgrammingLanguageLink.click();
        page.waitForLoadState(LoadState.NETWORKIDLE);
    }

    public void verifyArticlePageLoaded() {
        page.waitForLoadState(LoadState.NETWORKIDLE);
        page.locator("h1:has-text('Python (programming language)')").waitFor(new Locator.WaitForOptions().setTimeout(10000).setState(com.microsoft.playwright.options.WaitForSelectorState.VISIBLE));
    }

    public void takeScreenshot(String fileName) {
        page.screenshot(new Page.ScreenshotOptions().setPath(java.nio.file.Paths.get(fileName)));
    }

    public Locator getLoginLink() {
        return loginLink;
    }

    public Locator getCreateAccountLink() {
        return createAccountLink;
    }

    public void clickCreateAccountLink() {
        createAccountLink.click();
        page.waitForLoadState(LoadState.NETWORKIDLE);
    }

    public Locator getCreateAccountPageHeading() {
        return createAccountPageHeading;
    }

    public void clickSearchButton() {
        searchButton.click();
        page.waitForLoadState(LoadState.NETWORKIDLE);
    }

    public Locator getNoSearchResultsContainer() {
        return noSearchResultsContainer;
    }
}