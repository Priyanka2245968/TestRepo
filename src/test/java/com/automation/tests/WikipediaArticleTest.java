package com.automation.tests;

import com.automation.base.BaseTestManager;
import com.automation.pages.WikipediaArticlePage;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import org.testng.annotations.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class WikipediaArticleTest extends BaseTestManager {

    @Test
    public void testSearchForArticleAndViewContent() {
        WikipediaArticlePage pageObject = new WikipediaArticlePage(this);
        pageObject.navigateToWikipedia();
        pageObject.searchForArticle("HTML Tables");
        pageObject.clickSearchButton();
        pageObject.clickFirstResultLink();
        assertThat(getPage()).hasURL("https://en.wikipedia.org/wiki/HTML_element#Tables");
        pageObject.takeScreenshot("article-page.png");
    }

    @Test
    public void testWikipediaAccessibleAnonymously() {
        WikipediaArticlePage pageObject = new WikipediaArticlePage(this);
        pageObject.navigateToWikipedia();
        assertThat(getPage()).hasURL("https://www.wikipedia.org/");
        pageObject.takeScreenshot("wikipedia-home.png");
    }

    @Test
    public void testNoArticleSearchShowsBlankPage() {
        WikipediaArticlePage pageObject = new WikipediaArticlePage(this);
        pageObject.navigateToWikipedia();
        pageObject.clickSearchButton();
        assertThat(getPage()).hasURL("https://www.wikipedia.org/");
        pageObject.takeScreenshot("no-search-results.png");
    }
}