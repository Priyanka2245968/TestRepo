package com.automation.pages;

import com.automation.base.BaseTestManager;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;
import com.microsoft.playwright.options.WaitForSelectorState;

public class WikipediaArticlePage {
    private Page page;

    public WikipediaArticlePage(BaseTestManager testManager) {
        this.page = testManager.getPage();
    }

    public void navigateToWikipedia() {
        System.out.println("\ud83d\udccd Navigate to https://www.wikipedia.org/");
        page.navigate("https://www.wikipedia.org/");
    }

    public void searchForArticle(String query) {
        System.out.println("\ud83d\udccd In the 'Search Wikipedia' field, enter '" + query + "'");
        page.locator("input[name='search']").first().fill(query);
    }

    public void clickSearchButton() {
        System.out.println("\ud83d\udccd Click the 'Search' icon button");
        page.locator("button[type='submit']").first().click();
    }

    public void verifySearchResults(String query) {
        System.out.println("\ud83d\udccd Verify the search results page opens listing multiple articles related to '" + query + "'");
        page.waitForLoadState(LoadState.NETWORKIDLE);
        Locator resultsHeading = page.locator("h1:has-text('Search results')");
        resultsHeading.waitFor(new Locator.WaitForOptions().setTimeout(5000));
        resultsHeading.isVisible();
        // Add assertion
        org.testng.Assert.assertTrue(resultsHeading.isVisible(), "Search results heading is not visible");
    }

    public void clickFirstResult() {
        System.out.println("\ud83d\udccd Click the first search result");
        page.locator("div.mw-search-results li.mw-search-result:first-child a").first().click();
    }

    public void verifyArticleLoaded(String expectedTitle) {
        System.out.println("\ud83d\udccd Verify the article page for '" + expectedTitle + "' is loaded");
        page.waitForLoadState(LoadState.NETWORKIDLE);
        Locator articleTitle = page.locator("h1#firstHeading");
        articleTitle.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
        // Add assertion
        org.testng.Assert.assertEquals(articleTitle.textContent(), expectedTitle, "Article title does not match");
    }
}