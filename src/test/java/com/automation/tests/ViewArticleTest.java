package com.automation.tests;

import com.automation.base.BaseTestManager;
import com.automation.pages.WikipediaArticlePage;
import com.microsoft.playwright.Locator;
import org.testng.annotations.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.testng.Assert.assertTrue;

public class ViewArticleTest extends BaseTestManager {

    @Test
    public void testViewArticle() {
        WikipediaArticlePage pageObject = new WikipediaArticlePage(this);
        pageObject.navigateToWikipedia();
        pageObject.searchForArticle("Python programming language");
        assertTrue(pageObject.isArticlePageLoaded("Python (programming language)"));
        assertThat(pageObject.getTableOfContentsLocator()).isVisible();
        pageObject.takeScreenshot("article_page.png");
    }

    @Test
    public void testBlankSearch() {
        WikipediaArticlePage pageObject = new WikipediaArticlePage(this);
        pageObject.navigateToWikipedia();
        pageObject.searchForArticle("");
        page.waitForLoadState(com.microsoft.playwright.options.LoadState.NETWORKIDLE);
        Locator noResultsLocator = page.locator("div[role='status']");
        assertThat(noResultsLocator).isVisible();
        pageObject.takeScreenshot("blank_search.png");
    }

    @Test
    public void testLongQuery() {
        WikipediaArticlePage pageObject = new WikipediaArticlePage(this);
        String longQuery = "ThisIsAVeryLongArticleTitleThatExceedsTheMaximumLengthAllowedForAWikipediaArticleTitle";
        pageObject.navigateToWikipedia();
        pageObject.searchForArticle(longQuery);
        page.waitForLoadState(com.microsoft.playwright.options.LoadState.NETWORKIDLE);
        assertTrue(page.url().contains("Special:Search"));
        pageObject.takeScreenshot("long_query.png");
    }
}