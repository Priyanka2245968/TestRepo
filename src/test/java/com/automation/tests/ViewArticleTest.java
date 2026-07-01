package com.automation.tests;

import com.automation.base.BaseTestManager;
import com.automation.pages.WikipediaArticlePage;
import com.microsoft.playwright.Locator;
import org.testng.annotations.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class ViewArticleTest extends BaseTestManager {

    @Test
    public void testViewArticle() {
        WikipediaArticlePage pageObject = new WikipediaArticlePage(this);
        pageObject.navigateToWikipedia();
        pageObject.searchForArticle("Python programming language");
        assertThat(page).hasURL("Python_(programming_language)");
        assertThat(pageObject.getTableOfContentsLocator()).isVisible();
        pageObject.takeScreenshot("article_page.png");
    }

    @Test
    public void testBlankSearch() {
        WikipediaArticlePage pageObject = new WikipediaArticlePage(this);
        pageObject.navigateToWikipedia();
        pageObject.searchForArticle("");
        Locator noResultsLocator = page.locator("div[role='status']");
        noResultsLocator.waitFor();
        assertThat(noResultsLocator).isVisible();
        pageObject.takeScreenshot("blank_search.png");
    }

    @Test
    public void testLongQuery() {
        WikipediaArticlePage pageObject = new WikipediaArticlePage(this);
        String longQuery = "ThisIsAVeryLongArticleTitleThatExceedsTheMaximumLengthAllowedForAWikipediaArticleTitle";
        pageObject.navigateToWikipedia();
        pageObject.searchForArticle(longQuery);
        assertThat(page).hasURL("https://www.wikipedia.org/");
        pageObject.takeScreenshot("long_query.png");
    }
}