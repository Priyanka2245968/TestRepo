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
        System.out.println("📍 Navigate to https://www.wikipedia.org/");
        page.navigate("https://www.wikipedia.org/");
    }

    public void searchWikipedia(String query) {
        System.out.println("📍 In the 'Search Wikipedia' field, enter '" + query + "'");
        page.locator("input[name='search']").first().fill(query);
    }

    public void clickSearchButton() {
        System.out.println("📍 Click the 'Search Wikipedia' button or press Enter");
        page.locator("button[type='submit']").first().click();
    }

    public void clickArticleLink(String articleTitle) {
        System.out.println("📍 From the search results, click the '" + articleTitle + "' link");
        page.locator("a:has-text('" + articleTitle + "')").first().click();
    }

    public void verifyArticlePageLoaded() {
        System.out.println("📍 Verify the article page has loaded successfully");
        page.waitForLoadState(LoadState.NETWORKIDLE);
        Locator articleContent = page.locator(".mw-parser-output");
        articleContent.waitFor(new Locator.WaitForOptions().setTimeout(10000));
    }

    public void takeScreenshot(String filename) {
        page.screenshot(new com.microsoft.playwright.Page.ScreenshotOptions().setPath(java.nio.file.Paths.get(filename)));
    }
}