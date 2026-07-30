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
        assertThat(pageObject.getArticleTitle()).contains("HTML Tutorial");
        pageObject.takeScreenshot("wikipedia-article-view.png");
    }

    @Test
    public void testViewWikipediaWithoutSearch() {
        WikipediaArticleViewPage pageObject = new WikipediaArticleViewPage(this);
        pageObject.navigateToWikipedia();
        pageObject.clickOnThisDayLink();
        pageObject.waitForOnThisDayPage();
        assertThat(pageObject.getPageTitle()).contains("On this day");
        pageObject.takeScreenshot("wikipedia-on-this-day.png");
    }

    @Test
    public void testNegativeNoSearchTextProvided() {
        WikipediaArticleViewPage pageObject = new WikipediaArticleViewPage(this);
        pageObject.navigateToWikipedia();
        pageObject.clickSearchButton();
        assertThat(pageObject.getErrorMessage()).contains("Please enter a search term");
    }
}
