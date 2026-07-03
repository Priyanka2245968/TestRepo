package com.automation.pages;

import com.automation.base.BaseTestManager;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;

import java.nio.file.Paths;

public class ViewArticlePage {
    private final Page page;
    private final Locator searchBox;
    private final Locator articleTitle;

    public ViewArticlePage(BaseTestManager testManager) {
        this.page = testManager.getPage();
        this.searchBox = page.locator("textarea[name='q'], input[name='q']");
        this.articleTitle = page.locator("#firstHeading");
    }

    public void navigateToWikipedia() {
        page.navigate("https://www.wikipedia.org");
        page.waitForLoadState(LoadState.NETWORKIDLE);
    }

    public void searchForArticle(String articleName) {
        searchBox.fill(articleName);
        searchBox.press("Enter");
        page.waitForLoadState(LoadState.NETWORKIDLE);
    }

    public String getArticleTitle() {
        return articleTitle.textContent();
    }

    public void takeScreenshot(String filename) {
        page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get(filename)));
    }
}