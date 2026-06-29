package com.automation.pages;

import com.automation.base.BaseTestManager;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;

public class WikipediaArticlePage {
    private Page page;

    public WikipediaArticlePage(BaseTestManager testManager) {
        this.page = testManager.getPage();
    }

    private Locator searchInput = page.locator("#searchInput");
    private Locator searchButton = page.locator("button[type='submit']");
    private Locator photosynthesisLink = page.locator("a[href='/wiki/Photosynthesis']");

    public void navigateToWikipedia() {
        page.navigate("https://www.wikipedia.org/");
        page.waitForLoadState(LoadState.NETWORKIDLE);
    }

    public void searchForTerm(String term) {
        searchInput.fill(term);
        searchButton.click();
        page.waitForLoadState(LoadState.NETWORKIDLE);
    }

    public void viewArticle() {
        photosynthesisLink.click();
        page.waitForLoadState(LoadState.NETWORKIDLE);
    }

    public void takeScreenshot(String filename) {
        page.screenshot(new Page.ScreenshotOptions().setPath(java.nio.file.Paths.get(filename)));
    }
}