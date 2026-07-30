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
        pageObject.navigateToWikipediaHomepage();
        pageObject.enterSearchText("HTML");
        pageObject.clickSearchButton();
        pageObject.waitForSearchResultsLoad();
        pageObject.clickArticleLink("HTML");
        pageObject.waitForArticleLoad();
        pageObject.takeScreenshot("wikipedia-article-view-success.png");
    }

    @Test
    public void testAccessWithoutLogin() {
        WikipediaArticleViewPage pageObject = new WikipediaArticleViewPage(this);
        pageObject.navigateToWikipediaHomepage();
        pageObject.verifyHomepageAccessible();
        pageObject.takeScreenshot("wikipedia-access-without-login.png");
    }

    @Test
    public void testNegativeNoSearchInput() {
        WikipediaArticleViewPage pageObject = new WikipediaArticleViewPage(this);
        pageObject.navigateToWikipediaHomepage();
        pageObject.clickSearchButton();
        pageObject.waitForNoSearchResults();
        pageObject.takeScreenshot("wikipedia-no-search-input.png");
    }
}
