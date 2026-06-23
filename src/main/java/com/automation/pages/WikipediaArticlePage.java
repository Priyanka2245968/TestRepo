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

    public void navigateToWikipedia() {
        page.navigate("https://www.wikipedia.org/");
    }

    public void searchForTopic(String topic) {
        page.locator("input[name='search']").first().fill(topic);
        page.locator("button[type='submit']").first().click();
    }

    public void clickSearchResult(String resultLink) {
        page.waitForLoadState(LoadState.NETWORKIDLE);
        page.locator("//a[contains(., '" + resultLink + "')]").first().click();
    }

    public void takeScreenshot(String filename) {
        page.screenshot(new com.microsoft.playwright.Page.ScreenshotOptions().setPath(java.nio.file.Paths.get(filename)));
    }
}