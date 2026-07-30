package com.automation.tests;

import com.automation.base.BaseTestManager;
import com.automation.pages.WikipediaArticleViewPage;
import com.microsoft.playwright.options.LoadState;
import org.testng.annotations.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class WikipediaArticleViewTest extends BaseTestManager {

    @Test
    public void testHappyPathSearchAndViewArticle() {
        WikipediaArticleViewPage pageObject = new WikipediaArticleViewPage(this);
        pageObject.navigateToWikipedia();
        pageObject.searchForTerm("HTML Tutorial");
        pageObject.waitForSearchResults();
        pageObject.clickTopSearchResult();
        getPage().waitForLoadState(LoadState.NETWORKIDLE);
        assertThat(getPage()).hasTitle("HTML Tutorial - Wikipedia");
        pageObject.takeScreenshot("wikipedia-article-view.png");
    }

    @Test
    public void testViewWikipediaWithoutSearch() {
        WikipediaArticleViewPage pageObject = new WikipediaArticleViewPage(this);
        pageObject.navigateToWikipedia();
        pageObject.clickOnThisDayLink();
        getPage().waitForLoadState(LoadState.NETWORKIDLE);
        assertThat(getPage()).hasTitle("Wikipedia:On this day - Wikipedia");
        pageObject.takeScreenshot("wikipedia-on-this-day.png");
    }

    @Test
    public void testNegativeNoSearchTextProvided() {
        WikipediaArticleViewPage pageObject = new WikipediaArticleViewPage(this);
        pageObject.navigateToWikipedia();
        pageObject.clickSearchButton();
        getPage().waitForLoadState(LoadState.NETWORKIDLE);
        String errorMessage = pageObject.getErrorMessage();
        org.testng.Assert.assertTrue(errorMessage.contains("Please enter a search term"));
    }
}