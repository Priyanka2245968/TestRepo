package com.automation.pages;

import com.automation.base.BaseTestManager;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;
import org.testng.Assert;

public class WikipediaArticlePage {
    private Page page;
    private final String SEARCH_INPUT_SELECTOR = "input[name='search']"; 
    private final String SEARCH_BUTTON_SELECTOR = "button[type='submit']"; 

    public WikipediaArticlePage(BaseTestManager testManager) {
        this.page = testManager.getPage();
    }

    public void navigateToWikipedia() {
        System.out.println("\ud83d\udccd Navigate to https://www.wikipedia.org/");
        page.navigate("https://www.wikipedia.org/");
    }

    public void searchWikipedia(String query) {
        System.out.println("\ud83d\udccd In the 'Search Wikipedia' field, enter '" + query + "'");
        page.locator(SEARCH_INPUT_SELECTOR).first().fill(query);
    }

    public void clickSearchButton() {
        System.out.println("\ud83d\udccd Click the 'Search Wikipedia' button or press Enter");
        page.locator(SEARCH_BUTTON_SELECTOR).first().click();
    }

    public void clickArticleLink(String articleTitle) {
        System.out.println("\ud83d\udccd From the search results, click the '" + articleTitle + "' link");
        page.locator("a:has-text('" + articleTitle + "')").first().click();
    }

    public void verifyArticlePageLoaded(String expectedTitle) {
        System.out.println("\ud83d\udccd Verify the article page has loaded successfully");
        page.waitForLoadState(LoadState.NETWORKIDLE);
        Locator articleContent = page.locator(".mw-parser-output");
        Assert.assertTrue(articleContent.isVisible(), "Article content is not visible");
        Assert.assertEquals(page.title(), expectedTitle, "Article title does not match");
    }
}