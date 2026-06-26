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

    public void searchForArticle(String query) {
        System.out.println("📍 In the 'Search Wikipedia' field, enter '" + query + "'");
        page.locator("input[name='search']").first().fill(query);
    }

    public void clickSearchButton() {
        System.out.println("📍 Click the 'Search' icon button");
        page.locator("button[type='submit']").first().click();
    }

    public void verifySearchResults(String query) {
        System.out.println("📍 Verify the search results page opens listing multiple articles related to '" + query + "'");
        page.waitForLoadState(LoadState.NETWORKIDLE);
        Locator resultsHeading = page.locator("h1:has-text('Search results')");
        resultsHeading.waitFor(new Locator.WaitForOptions().setTimeout(5000));
        resultsHeading.isVisible();
    }

    public void clickFirstResult() {
        System.out.println("📍 Click the first result titled 'Python (programming language)'");
        page.locator("a:has-text('Python (programming language)')").first().click();
    }

    public void verifyArticleLoaded(String title) {
        System.out.println("📍 Verify the article page titled '" + title + "' loads");
        page.waitForLoadState(LoadState.NETWORKIDLE);
        Locator articleTitle = page.locator("h1:has-text('" + title + "')");
        articleTitle.waitFor(new Locator.WaitForOptions().setTimeout(5000));
        articleTitle.isVisible();
    }

    public void takeScreenshot(String filename) {
        page.screenshot(new com.microsoft.playwright.Page.ScreenshotOptions().setPath(java.nio.file.Paths.get(filename)));
    }
}