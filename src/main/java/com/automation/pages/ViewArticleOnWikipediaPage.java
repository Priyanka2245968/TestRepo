package com.automation.pages;

import com.automation.base.BaseTestManager;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;

public class ViewArticleOnWikipediaPage {
    private Page page;
    private Locator searchInput;
    private Locator searchButton;
    private Locator photosynthesisLink;
    private Locator searchErrorMessage;

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
        page.waitForLoadState(LoadState.NETWORKIDLE);
    }

    public void clickPhotosynthesisLink() {
        photosynthesisLink.click();
        page.waitForLoadState(LoadState.NETWORKIDLE);
    }

    public String getSearchErrorMessage() {
        return searchErrorMessage.textContent();
    }

    public void takeScreenshot(String fileName) {
        page.screenshot(new Page.ScreenshotOptions().setPath(java.nio.file.Paths.get(fileName)));
    }
}