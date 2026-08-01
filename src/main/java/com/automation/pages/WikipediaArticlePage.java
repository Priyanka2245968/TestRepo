package com.automation.pages;

import com.automation.base.BaseTestManager;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;

import java.io.PrintStream;
import java.nio.file.Paths;

public class WikipediaArticlePage {
    private final Page page;
    private final Locator searchInput;
    private final Locator searchButton;
    private final Locator firstResultLink;

    private static final String SEARCH_INPUT_SELECTOR = "#searchInput";
    private static final String SEARCH_BUTTON_SELECTOR = "//button[contains(normalize-space(.),\"Search\")]";
    private static final String FIRST_RESULT_LINK_SELECTOR = "button[type='submit']";

    public WikipediaArticlePage(BaseTestManager testManager) {
        this.page = testManager.getPage();
        this.searchInput = page.locator(SEARCH_INPUT_SELECTOR);
        this.searchButton = page.locator(SEARCH_BUTTON_SELECTOR).first();
        this.firstResultLink = page.locator(FIRST_RESULT_LINK_SELECTOR);
    }

    public void navigateToWikipedia() {
        page.navigate("https://www.wikipedia.org/");
        page.waitForLoadState(LoadState.NETWORKIDLE);
    }

    public void searchForArticle(String query) {
        PrintStream out = System.out;
        out.println("\ud83d\udccd Searching for article: " + query);
        searchInput.fill(query);
    }

    public void clickSearchButton() {
        PrintStream out = System.out;
        out.println("\ud83d\udccd Clicking search button");
        searchButton.click();
        page.waitForLoadState(LoadState.NETWORKIDLE);
    }

    public void clickFirstResultLink() {
        PrintStream out = System.out;
        out.println("\ud83d\udccd Clicking first result link");
        firstResultLink.click();
        page.waitForLoadState(LoadState.NETWORKIDLE);
    }

    public void takeScreenshot(String fileName) {
        page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get(fileName)));
    }
}