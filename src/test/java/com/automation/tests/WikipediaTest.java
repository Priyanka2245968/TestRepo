package com.automation.tests;

import com.automation.base.BaseTestManager;
import com.automation.pages.WikipediaPage;
import org.testng.annotations.Test;

public class WikipediaTest extends BaseTestManager {

    @Test
    public void testHappyPathViewWikipediaArticle() {
        WikipediaPage pageObject = new WikipediaPage(this);
        pageObject.navigateToWikipedia();
        pageObject.searchForTerm("HTML Tables");
        pageObject.clickSearchButton();
        pageObject.clickFirstSearchResult();
        pageObject.takeScreenshot("wikipedia-article.png");
    }

    @Test
    public void testArticleContentReadability() {
        WikipediaPage pageObject = new WikipediaPage(this);
        pageObject.navigateToWikipedia();
        pageObject.searchForTerm("HTML Tables");
        pageObject.clickSearchButton();
        pageObject.clickFirstSearchResult();
        // Scroll through the article content
        pageObject.takeScreenshot("wikipedia-article-readability.png");
    }

    @Test
    public void testNegativeBlankSearch() {
        WikipediaPage pageObject = new WikipediaPage(this);
        pageObject.navigateToWikipedia();
        pageObject.clickSearchButton();
        pageObject.takeScreenshot("wikipedia-blank-search.png");
    }
}