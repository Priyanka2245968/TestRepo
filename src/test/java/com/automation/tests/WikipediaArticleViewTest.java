package com.automation.tests;

import com.automation.base.BaseTestManager;
import com.automation.pages.WikipediaArticleViewPage;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.options.LoadState;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class WikipediaArticleViewTest extends BaseTestManager {

    @Test
    public void testHappyPathSearchAndViewArticle() {
        WikipediaArticleViewPage pageObject = new WikipediaArticleViewPage(this);
        pageObject.navigateToWikipedia();
        pageObject.searchForTerm("HTML Tutorial");
        pageObject.waitForSearchResults();
        pageObject.clickTopSearchResult();
        String articleTitle = pageObject.getArticleTitle();
        assertTrue(articleTitle.contains("HTML Tutorial"));
        pageObject.takeScreenshot("wikipedia-article-view.png");
    }

    @Test
    public void testViewWikipediaWithoutSearch() {
        WikipediaArticleViewPage pageObject = new WikipediaArticleViewPage(this);
        pageObject.navigateToWikipedia();
        pageObject.clickOnThisDayLink();
        pageObject.waitForOnThisDayPage();
        String pageTitle = pageObject.getPageTitle();
        assertTrue(pageTitle.contains("On this day"));
        pageObject.takeScreenshot("wikipedia-on-this-day.png");
    }

    @Test
    public void testNegativeNoSearchTextProvided() {
        WikipediaArticleViewPage pageObject = new WikipediaArticleViewPage(this);
        pageObject.navigateToWikipedia();
        pageObject.clickSearchButton();
        String errorMessage = pageObject.getErrorMessage();
        assertTrue(errorMessage.contains("Please enter a search term"));
    }
}