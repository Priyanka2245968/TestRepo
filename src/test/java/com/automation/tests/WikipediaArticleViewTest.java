package com.automation.tests;

import com.automation.base.BaseTestManager;
import com.automation.pages.WikipediaArticleViewPage;
import com.microsoft.playwright.options.LoadState;
import org.testng.annotations.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class WikipediaArticleViewTest extends BaseTestManager {

    @Test
    public void testHappyPath_SearchAndViewArticle() {
        WikipediaArticleViewPage pageObject = new WikipediaArticleViewPage(this);
        pageObject.navigateToWikipedia();
        pageObject.searchForArticle("Photosynthesis");
        pageObject.clickSearchButton();
        pageObject.waitForArticleLoad();
        assertThat(getPage()).hasTitle("Photosynthesis - Wikipedia");
        pageObject.takeScreenshot("wikipedia-photosynthesis-article.png");
    }

    @Test
    public void testNegative_BlankSearchField() {
        WikipediaArticleViewPage pageObject = new WikipediaArticleViewPage(this);
        pageObject.navigateToWikipedia();
        pageObject.clickSearchButton();
        pageObject.waitForBlankSearchErrorMessage();
        assertThat(pageObject.getBlankSearchErrorMessage()).isVisible();
        pageObject.takeScreenshot("wikipedia-blank-search-error.png");
    }

    @Test
    public void testNegative_LongSearchQuery() {
        WikipediaArticleViewPage pageObject = new WikipediaArticleViewPage(this);
        pageObject.navigateToWikipedia();
        pageObject.searchForArticle("This is a very long search query that should not return any results on Wikipedia");
        pageObject.clickSearchButton();
        pageObject.waitForNoResultsMessage();
        assertThat(pageObject.getNoResultsMessage()).isVisible();
        pageObject.takeScreenshot("wikipedia-long-search-no-results.png");
    }
}