package com.automation.tests;

import com.automation.base.BaseTestManager;
import com.automation.pages.ViewArticleOnWikipediaPage;
import com.microsoft.playwright.Locator;
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
        pageObject.waitForSearchResults();
        pageObject.verifyNoSearchResultsShown();
    }
}