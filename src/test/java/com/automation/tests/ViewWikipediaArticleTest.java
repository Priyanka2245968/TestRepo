package com.automation.tests;

import com.automation.base.BaseTestManager;
import com.automation.pages.ViewWikipediaArticlePage;
import com.microsoft.playwright.options.LoadState;
import org.testng.annotations.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class ViewWikipediaArticleTest extends BaseTestManager {

    @Test
    public void testHappyPathViewWikipediaArticleForATopic() {
        ViewWikipediaArticlePage pageObject = new ViewWikipediaArticlePage(this);
        pageObject.navigateToWikipediaHomePage();
        pageObject.fillSearchField("HTML Tables");
        pageObject.clickSearchButton();
        pageObject.waitForSearchResults();
        pageObject.clickTopSearchResult();
        pageObject.waitForArticleLoad();
        assertThat(getPage()).hasTitle("HTML table - Wikipedia");
        pageObject.takeScreenshot("wikipedia-article.png");
    }

    @Test
    public void testNegativeBlankSearchQuery() {
        ViewWikipediaArticlePage pageObject = new ViewWikipediaArticlePage(this);
        pageObject.navigateToWikipediaHomePage();
        pageObject.clickSearchButton();
        pageObject.waitForNoResults();
        assertThat(getPage()).hasTitle("Wikipedia");
        pageObject.takeScreenshot("blank-search.png");
    }

    @Test
    public void testNegativeSearchQueryExceedingMaxLength() {
        ViewWikipediaArticlePage pageObject = new ViewWikipediaArticlePage(this);
        pageObject.navigateToWikipediaHomePage();
        String longQuery = "A string longer than 500 characters";
        for (int i = 0; i < 10; i++) {
            longQuery += longQuery;
        }
        pageObject.fillSearchField(longQuery);
        pageObject.clickSearchButton();
        pageObject.waitForNoResults();
        assertThat(getPage()).hasTitle("Wikipedia");
        pageObject.takeScreenshot("long-search.png");
    }
}
