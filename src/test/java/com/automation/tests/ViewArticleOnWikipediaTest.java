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
        pageObject.searchForTopic("Python programming language");
        pageObject.clickSearchButton();
        pageObject.waitForSearchResults();
        pageObject.clickPythonProgrammingLanguageLink();
        pageObject.verifyPythonArticlePageLoaded();
        pageObject.takeScreenshot("python-article.png");
    }

    @Test
    public void testArticleContentReadable() {
        ViewArticleOnWikipediaPage pageObject = new ViewArticleOnWikipediaPage(this);
        pageObject.navigateToWikipedia();
        pageObject.searchForTopic("Python programming language");
        pageObject.clickSearchButton();
        pageObject.waitForSearchResults();
        pageObject.clickPythonProgrammingLanguageLink();
        pageObject.verifyArticleContentReadable();
        pageObject.takeScreenshot("python-article-content.png");
    }

    @Test
    public void testNoSearchTermShowsBlankPage() {
        ViewArticleOnWikipediaPage pageObject = new ViewArticleOnWikipediaPage(this);
        pageObject.navigateToWikipedia();
        pageObject.clickSearchButton();
        pageObject.waitForBlankSearchResults();
        pageObject.verifyBlankSearchResultsMessage();
        pageObject.takeScreenshot("blank-search-results.png");
    }

    @Test
    public void testNegativeWhenVeryLongTextIsProvided() {
        ViewArticleOnWikipediaPage pageObject = new ViewArticleOnWikipediaPage(this);
        pageObject.navigateToWikipedia();
        pageObject.enterVeryLongTextInSearchBox();
        pageObject.clickSearchButton();
        assertTrue(pageObject.getErrorMessage().contains("Search request is longer than the maximum allowed length"));
        pageObject.takeScreenshot("very-long-text-error.png");
    }

    @Test
    public void testNegativeWhenInvalidTextIsProvided() {
        ViewArticleOnWikipediaPage pageObject = new ViewArticleOnWikipediaPage(this);
        pageObject.navigateToWikipedia();
        pageObject.enterInvalidTextInSearchBox();
        pageObject.clickSearchButton();
        assertThat(pageObject.getNoResultsMessage()).containsText("There were no results matching the query");
        pageObject.takeScreenshot("invalid-text-error.png");
    }
}