package com.automation.pages;

import com.automation.base.BaseTestManager;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;

import java.nio.file.Paths;

public class ViewArticleOnWikipediaPage {
    private final Page page;
    public final Locator searchInput, searchButton, articleLink;

    public ViewArticleOnWikipediaPage(BaseTestManager testManager) {
        this.page = testManager.getPage();
        this.searchInput = page.locator("#searchInput");
        this.searchButton = page.locator("button[type='submit']");
        this.articleLink = page.locator("//a[contains(@href, '/wiki/HTML')]");
    }

    public void navigateToWikipedia() {
        page.navigate("https://www.wikipedia.org/");
    }

    public void fillSearchField(String text) {
        System.out.println("\ud83d\udccd Fill 'Search Wikipedia' field with: " + text);
        searchInput.fill(text);
    }

    public void clickSearchButton() {
        System.out.println("\ud83d\udccd Click 'Search' icon button");
        searchButton.click();
        page.waitForLoadState(LoadState.NETWORKIDLE);
    }

    public void clickArticleLink() {
        System.out.println("\ud83d\udccd Click link for the 'HTML' article in the search results");
        articleLink.click();
        page.waitForLoadState(LoadState.NETWORKIDLE);
    }

    public void takeScreenshot(String filename) {
        page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get(filename)));
    }
}