package com.automation.tests;

import com.automation.base.BaseTestManager;
import com.automation.pages.WikipediaArticlePage;
import org.testng.annotations.Test;

public class ViewArticleOnWikipediaTest extends BaseTestManager {

    @Test
    public void testHappyPath() {
        WikipediaArticlePage pageObject = new WikipediaArticlePage(this);
        pageObject.navigateToWikipedia();
        pageObject.searchForArticle("HTML Table Element");
        pageObject.clickSearchButton();
        pageObject.clickFirstResultLink();
        pageObject.takeScreenshot("happy-path.png");
    }

    @Test
    public void testNegativeNoSearchTerm() {
        WikipediaArticlePage pageObject = new WikipediaArticlePage(this);
        pageObject.navigateToWikipedia();
        pageObject.searchForArticle("");
        pageObject.clickSearchButton();
        pageObject.takeScreenshot("negative-no-search-term.png");
    }

    @Test
    public void testNegativeLongSearchTerm() {
        WikipediaArticlePage pageObject = new WikipediaArticlePage(this);
        String longString = "A".repeat(501);
        pageObject.navigateToWikipedia();
        pageObject.searchForArticle(longString);
        pageObject.clickSearchButton();
        pageObject.takeScreenshot("negative-long-search-term.png");
    }

    @Test
    public void testNegativeInvalidSearchTerm() {
        WikipediaArticlePage pageObject = new WikipediaArticlePage(this);
        pageObject.navigateToWikipedia();
        pageObject.searchForArticle("kj#@$%lkj");
        pageObject.clickSearchButton();
        pageObject.takeScreenshot("negative-invalid-search-term.png");
    }
}