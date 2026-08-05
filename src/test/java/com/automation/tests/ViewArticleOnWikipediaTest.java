package com.automation.tests;

import com.automation.base.BaseTestManager;
import com.automation.pages.ViewArticleOnWikipediaPage;
import com.automation.pages.WikipediaArticlePage;
import com.microsoft.playwright.options.LoadState;
import org.testng.annotations.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class ViewArticleOnWikipediaTest extends BaseTestManager {

    @Test
    public void testHappyPathViewArticleOnWikipedia() {
        ViewArticleOnWikipediaPage pageObject = new ViewArticleOnWikipediaPage(this);
        pageObject.navigateToWikipediaHomepage();
        pageObject.searchForTerm("HTML Tables");
        pageObject.clickSearchButton();
        pageObject.waitForSearchResultsPage();
        pageObject.clickFirstSearchResult();
        pageObject.waitForArticlePage();
        pageObject.takeScreenshot("wikipedia-article-page.png");
    }

    @Test
    public void testWikipediaAccessibleWithoutLogin() {
        ViewArticleOnWikipediaPage pageObject = new ViewArticleOnWikipediaPage(this);
        pageObject.navigateToWikipediaHomepage();
        assertThat(getPage()).hasTitle("Wikipedia");
        pageObject.verifyHomepageContentAccessible();
    }

    @Test
    public void testNegativeNoSearchTerm() {
        ViewArticleOnWikipediaPage pageObject = new ViewArticleOnWikipediaPage(this);
        pageObject.navigateToWikipediaHomepage();
        pageObject.clickSearchButton();
        assertThat(getPage()).hasURL("https://en.wikipedia.org/wiki/Special:Search?search=&go=Go");
    }
}