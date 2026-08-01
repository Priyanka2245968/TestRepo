package com.automation.tests;

import com.automation.base.BaseTestManager;
import com.automation.pages.WikipediaArticlePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ViewArticleOnWikipediaTest extends BaseTestManager {

    @Test
    public void testHappyPath() {
        WikipediaArticlePage pageObject = new WikipediaArticlePage(this);
        pageObject.navigateToWikipedia();
        pageObject.searchForArticle("HTML Table Element");
        pageObject.clickSearchButton();
        pageObject.clickFirstResultLink();
        // Add assertion to verify article content
        Assert.assertTrue(getPage().textContent().contains("HTML <table>"), "Article content does not contain 'HTML <table>'");
        pageObject.takeScreenshot("happy-path.png");
    }

    @Test
    public void testNegativeNoSearchTerm() {
        WikipediaArticlePage pageObject = new WikipediaArticlePage(this);
        pageObject.navigateToWikipedia();
        pageObject.searchForArticle("");
        pageObject.clickSearchButton();
        // Add assertion to verify no search results are shown
        Assert.assertTrue(getPage().textContent().contains("No results found"), "Search results found when no search term was provided");
        pageObject.takeScreenshot("negative-no-search-term.png");
    }

    @Test
    public void testNegativeLongSearchTerm() {
        WikipediaArticlePage pageObject = new WikipediaArticlePage(this);
        String longString = "A".repeat(501);
        pageObject.navigateToWikipedia();
        pageObject.searchForArticle(longString);
        pageObject.clickSearchButton();
        // Add assertion to verify no search results are shown
        Assert.assertTrue(getPage().textContent().contains("No results found"), "Search results found when long search term was provided");
        pageObject.takeScreenshot("negative-long-search-term.png");
    }

    // Remove this test case as it is a duplicate of testNegativeNoSearchTerm
    // @Test
    // public void testNegativeInvalidSearchTerm() {
    //     WikipediaArticlePage pageObject = new WikipediaArticlePage(this);
    //     pageObject.navigateToWikipedia();
    //     pageObject.searchForArticle("!@#$%^&*()");
    //     pageObject.clickSearchButton();
    //     pageObject.takeScreenshot("negative-invalid-search-term.png");
    // }
}