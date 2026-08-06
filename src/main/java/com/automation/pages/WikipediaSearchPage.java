package com.automation.pages;

import com.automation.base.BaseTestManager;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;

public class WikipediaSearchPage {
    private final Page page;
    private final Locator searchInput;
    private final Locator searchButton;
    private final Locator htmlLink;

    public WikipediaSearchPage(BaseTestManager testManager) {
        this.page = testManager.getPage();
        this.searchInput = page.locator("#searchInput");
        this.searchButton = page.locator("button[type='submit']");
        this.htmlLink = page.locator("//a[contains(@href, '/wiki/HTML')]");
    }

    public void navigateToWikipedia() {
        page.navigate("https://www.wikipedia.org/");
    }

    public void searchForTerm(String term) {
        System.out.println("📍 Entering search term: " + term);
        searchInput.fill(term);
    }

    public void clickSearchButton() {
        System.out.println("📍 Clicking search button");
        searchButton.click();
        page.waitForLoadState(LoadState.NETWORKIDLE);
    }

    public void clickHtmlLink() {
        System.out.println("📍 Clicking HTML link");
        htmlLink.click();
        page.waitForLoadState(LoadState.NETWORKIDLE);
    }

    public void takeScreenshot(String filename) {
        page.screenshot(new Page.ScreenshotOptions().