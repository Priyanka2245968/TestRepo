package com.automation.tests;

import com.automation.base.BaseTestManager;
import com.automation.pages.WikipediaArticlePage;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class WikipediaArticleTest extends BaseTestManager {

    @Test
    public void testSearchForArticleAndViewContent() {
        WikipediaArticlePage pageObject = new WikipediaArticlePage(this);
        pageObject.navigateToWikipedia();
        pageObject.searchForArticle("HTML Tables");
        pageObject.clickSearchButton();
        String currentUrl = getPage().url();
        assertTrue(currentUrl.contains("HTML_element#Tables"));
        pageObject.takeScreenshot("article-page.png");
    }

    @Test
    public void testWikipediaAccessibleAnonymously() {
        WikipediaArticlePage pageObject = new WikipediaArticlePage(this);
        pageObject.navigateToWikipedia();
        String currentUrl = getPage().url();
        assertTrue(currentUrl.equals("https://www.wikipedia.org/"));
        pageObject.takeScreenshot("wikipedia-home.png");
    }

    @Test
    public void testNoArticleSearchShowsBlankPage() {
        WikipediaArticlePage pageObject = new WikipediaArticlePage(this);
        pageObject.navigateToWikipedia();
        pageObject.clickSearchButton();
        String currentUrl = getPage().url();
        assertTrue(currentUrl.equals("https://www.wikipedia.org/"));
        pageObject.takeScreenshot("no-search-results.png");
    }
}