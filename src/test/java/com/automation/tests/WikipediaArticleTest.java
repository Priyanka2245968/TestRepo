package com.automation.tests;

import com.automation.base.BaseTestManager;
import com.automation.pages.WikipediaArticlePage;
import org.testng.annotations.Test;

public class WikipediaArticleTest extends BaseTestManager {

    @Test
    public void testViewWikipediaArticleForSearchedTopic() {
        WikipediaArticlePage pageObject = new WikipediaArticlePage(this);
        pageObject.navigateToWikipedia();
        pageObject.searchForTerm("HTML Tutorial");
        pageObject.clickSearchButton();
        pageObject.takeScreenshot("wikipedia-search-result.png");
    }

    @Test
    public void testAnonymousAccessToWikipedia() {
        WikipediaArticlePage pageObject = new WikipediaArticlePage(this);
        pageObject.navigateToWikipedia();
        pageObject.takeScreenshot("wikipedia-homepage.png");
    }

    @Test
    public void testNoSearchTextProvided() {
        WikipediaArticlePage pageObject = new WikipediaArticlePage(this);
        pageObject.navigateToWikipedia();
        pageObject.clickSearchButton();
        pageObject.takeScreenshot("wikipedia-no-search-text.png");
    }
}