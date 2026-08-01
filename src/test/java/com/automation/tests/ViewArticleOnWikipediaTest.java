package com.automation.tests;

import com.automation.base.BaseTestManager;
import com.automation.pages.WikipediaArticlePage;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class ViewArticleOnWikipediaTest extends BaseTestManager {

    @Test
    public void testHappyPath() {
        WikipediaArticlePage pageObject = new WikipediaArticlePage(this);
        pageObject.navigateToWikipedia();
        pageObject.searchForArticle("HTML Table Element");
        pageObject.clickSearchButton();
        String currentUrl = getPage().url();
        assertTrue(currentUrl.contains("HTML_element#Tables"));
        pageObject.takeScreenshot("happy-path.png");
    }

    @Test
    public void testNegativeNoSearchTerm() {
        WikipediaArticlePage pageObject = new WikipediaArticlePage(this);
        pageObject.navigateToWikipedia();
        pageObject.searchForArticle("");
        pageObject.clickSearchButton();
        String currentUrl = getPage().url();
        assertTrue(currentUrl.equals("https://www.wikipedia.org/"));
        pageObject.takeScreenshot("negative-no-search-term.png");
    }

    @Test
    public void testNegativeLongSearchTerm() {
        WikipediaArticlePage pageObject = new WikipediaArticlePage(this);
        String longString = "A".repeat(501);
        pageObject.navigateToWikipedia();
        pageObject.searchForArticle(longString);
        pageObject.clickSearchButton();
        String currentUrl = getPage().url();
        assertTrue(currentUrl.contains("Special:Search?search=A+string+longer+than+500+characters&go=Go&ns0=1"));
        pageObject.takeScreenshot("negative-long-search-term.png");
    }
}