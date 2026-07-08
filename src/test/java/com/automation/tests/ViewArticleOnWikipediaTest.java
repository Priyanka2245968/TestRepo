package com.automation.tests;

import com.automation.base.BaseTestManager;
import com.automation.pages.ViewArticleOnWikipediaPage;
import com.microsoft.playwright.options.LoadState;
import org.testng.annotations.Test;

public class ViewArticleOnWikipediaTest extends BaseTestManager {

    @Test
    public void testHappyPathSearchAndViewArticle() {
        ViewArticleOnWikipediaPage pageObject = new ViewArticleOnWikipediaPage(this);
        pageObject.navigateToWikipedia();
        pageObject.searchForArticle("Python programming language");
        pageObject.clickSearchButton();
        pageObject.waitForSearchResults();
        pageObject.clickArticleLink("Python (programming language)");
        pageObject.waitForArticleLoad();
        pageObject.takeScreenshot("python-article.png");
    }

    @Test
    public void testArticleContentSuitableForLearning() {
        ViewArticleOnWikipediaPage pageObject = new ViewArticleOnWikipediaPage(this);
        pageObject.navigateToArticle("https://www.wikipedia.org/wiki/Python_(programming_language)");
        pageObject.waitForArticleLoad();
        pageObject.verifyArticleStructure();
        pageObject.takeScreenshot("python-article-structure.png");
    }

    @Test
    public void testNegativeNoSearchTerm() {
        ViewArticleOnWikipediaPage pageObject = new ViewArticleOnWikipediaPage(this);
        pageObject.navigateToWikipedia();
        pageObject.clickSearchButton();
        pageObject.waitForNoResultsMessage();
        pageObject.takeScreenshot("no-search-results.png");
    }
}
