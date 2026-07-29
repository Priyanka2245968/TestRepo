package com.automation.pages;

import com.automation.base.BaseTestManager;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;

public class WikipediaArticlePage {
    private final Page page;
    public final Locator articleTitle;

    public WikipediaArticlePage(BaseTestManager testManager) {
        this.page = testManager.getPage();
        this.articleTitle = page.locator("//h1[@id='firstHeading' or @class='firstHeading']");
    }

    public void navigateToArticle(String articleName) {
        page.navigate("https://en.wikipedia.org/wiki/" + articleName);
        page.waitForLoadState(LoadState.NETWORKIDLE);
    }

    public String getArticleTitle() {
        return articleTitle.textContent();
    }

    public void takeScreenshot(String filename) {
        page.screenshot(new Page.ScreenshotOptions().setPath(java.nio.file.Paths.get(filename)));
    }
}