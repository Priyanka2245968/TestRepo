package com.automation.pages;

import com.automation.base.BaseTestManager;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;

public class ViewArticleOnWikipediaPage {
    private final Page page;
    private final Locator searchInput;
    private final Locator searchButton;
    private final Locator photosynthesisLink;
    private final Locator searchErrorMessage;

    public ViewArticleOnWikipediaPage(BaseTestManager testManager) {
        this.page = testManager.getPage();
        this.searchInput = page.locator("#searchInput");
        this.searchButton = page.locator("button[type='submit']");
        this.photosynthesisLink = page.locator("a[href='/wiki/Photosynthesis']");
        this.searchErrorMessage = page.locator(".mw-search-errorbox");
    }

    public void navigateToWikipediaHomepage() {
        page.navigate("https://www.wikipedia.org/");
        page.waitForLoadState(LoadState.NETWORKIDLE);
    }

    public void searchForTopic(String topic) {
        searchInput.fill(topic);
    }

    public void clickSearchButton() {
        searchButton.click();
    }

    public void clickPhotosynthesisLink() {
        photosynthesisLink.click();
        page.waitForLoadState(LoadState.NETWORKIDLE);
    }

    public Page getPage() {
        return page;
    }

    public Locator getSearchErrorMessage() {
        return searchErrorMessage;
    }

    public void takeScreenshot(String filename) {
        page.screenshot(new Page.ScreenshotOptions().setPath(java.nio.file.Paths.get(filename)));
    }
}
