package com.automation.tests;

import com.automation.base.BaseTestManager;
import com.automation.pages.WikipediaPage;
import org.testng.annotations.Test;
import org.testng.Assert;

public class WikipediaTest extends BaseTestManager {

    @Test
    public void testHappyPathViewWikipediaArticle() {
        WikipediaPage pageObject = new WikipediaPage(this);
        pageObject.navigateToWikipedia();
        pageObject.searchForTerm("HTML Tables");
        pageObject.clickSearchButton();
        pageObject.clickFirstSearchResult();
        pageObject.assertArticleTitle("HTML element - Wikipedia");
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
        pageObject.assertArticleTitle("HTML element - Wikipedia");
        pageObject.takeScreenshot("wikipedia-article-readability.png");
    }

    @Test
    public void testNegativeBlankSearch() {
        WikipediaPage pageObject = new WikipediaPage(this);
        pageObject.navigateToWikipedia();
        pageObject.clickSearchButton();
        String expectedTitle = "Wikipedia";
        String actualTitle = getPage().title();
        Assert.assertEquals(actualTitle, expectedTitle, "Page title does not match for blank search");
        pageObject.takeScreenshot("wikipedia-blank-search.png");
    }
}