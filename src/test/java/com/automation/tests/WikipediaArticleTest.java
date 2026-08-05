package com.automation.tests;

import com.automation.base.BaseTestManager;
import com.automation.pages.WikipediaArticlePage;
import com.microsoft.playwright.options.LoadState;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class WikipediaArticleTest extends BaseTestManager {

    private static final String WIKIPEDIA_URL = "https://www.wikipedia.org/";
    private static final String SEARCH_TERM = "HTML";

    @Test
    public void testHappyPathSearchAndViewArticle() {
        WikipediaArticlePage pageObject = new WikipediaArticlePage(this);
        page.navigate(WIKIPEDIA_URL);
        pageObject.fillSearchField(SEARCH_TERM);
        pageObject.clickSearchButton();
        page.waitForLoadState(LoadState.NETWORKIDLE);
        pageObject.clickArticleLink(SEARCH_TERM);
        pageObject.takeScreenshot("wikipedia-article-test.png");
    }

    @Test
    public void testArticleContentIsReadable() {
        WikipediaArticlePage pageObject = new WikipediaArticlePage(this);
        page.navigate(WIKIPEDIA_URL);
        pageObject.fillSearchField(SEARCH_TERM);
        pageObject.clickSearchButton();
        page.waitForLoadState(LoadState.NETWORKIDLE);
        pageObject.clickArticleLink(SEARCH_TERM);
        assertTrue(pageObject.verifyArticleContentIsReadable(), "Article content is not readable");
        pageObject.takeScreenshot("wikipedia-article-readable-test.png");
    }

    @Test
    public void testNegativeNoSearchTerm() {
        WikipediaArticlePage pageObject = new WikipediaArticlePage(this);
        page.navigate(WIKIPEDIA_URL);
        pageObject.clickSearchButton();
        assertTrue(pageObject.verifyNoResultsDisplayed(), "No results message not displayed");
    }
}