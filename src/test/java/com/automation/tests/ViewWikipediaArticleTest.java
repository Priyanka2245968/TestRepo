package com.automation.tests;

import com.automation.base.BaseTestManager;
import com.automation.pages.ViewWikipediaArticlePage;
import com.microsoft.playwright.Page;
import org.testng.annotations.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class ViewWikipediaArticleTest extends BaseTestManager {

    private static final String WIKIPEDIA_URL = "https://www.wikipedia.org/";
    private static final String EXPECTED_ARTICLE_TITLE = "HTML table - Wikipedia";
    private static final String EXPECTED_HOME_PAGE_TITLE = "Wikipedia";
    private static final String SEARCH_QUERY = "HTML Tables";
    private static final int MAX_SEARCH_QUERY_LENGTH = 300;

    @Test
    public void testHappyPathViewWikipediaArticleForATopic() {
        ViewWikipediaArticlePage pageObject = new ViewWikipediaArticlePage(this);
        pageObject.navigateToUrl(WIKIPEDIA_URL);
        pageObject.fillSearchField(SEARCH_QUERY);
        pageObject.clickSearchButton();
        pageObject.waitForSearchResults();
        pageObject.clickTopSearchResult();
        Page page = getPage();
        assertThat(page).hasURL(containsString(EXPECTED_ARTICLE_TITLE.toLowerCase().replace(" ", "_")));
        assertThat(page).hasTitle(EXPECTED_ARTICLE_TITLE);
        pageObject.takeScreenshot("wikipedia-article.png");
    }

    @Test
    public void testNegativeBlankSearchQuery() {
        ViewWikipediaArticlePage pageObject = new ViewWikipediaArticlePage(this);
        pageObject.navigateToUrl(WIKIPEDIA_URL);
        pageObject.clickSearchButton();
        pageObject.waitForNoResults();
        Page page = getPage();
        assertThat(page).hasURL(WIKIPEDIA_URL);
        assertThat(page).hasTitle(EXPECTED_HOME_PAGE_TITLE);
        pageObject.takeScreenshot("blank-search.png");
    }

    @Test
    public void testNegativeSearchQueryExceedingMaxLength() {
        ViewWikipediaArticlePage pageObject = new ViewWikipediaArticlePage(this);
        pageObject.navigateToUrl(WIKIPEDIA_URL);
        String longQuery = "x".repeat(MAX_SEARCH_QUERY_LENGTH + 1);
        pageObject.fillSearchField(longQuery);
        pageObject.clickSearchButton();
        pageObject.waitForNoResults();
        Page page = getPage();
        assertThat(page).hasURL(WIKIPEDIA_URL);
        assertThat(page).hasTitle(EXPECTED_HOME_PAGE_TITLE);
        pageObject.takeScreenshot("long-search.png");
    }
}