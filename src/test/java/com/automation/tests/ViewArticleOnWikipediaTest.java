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
        pageObject.verifySearchResultsLoaded();
        pageObject.clickPythonArticleLink();
        pageObject.verifyPythonArticleLoaded();
        pageObject.takeScreenshot("python-article.png");
    }

    @Test
    public void testArticleContentSuitableForLearning() {
        ViewArticleOnWikipediaPage pageObject = new ViewArticleOnWikipediaPage(this);
        pageObject.navigateToWikipedia();
        pageObject.searchForArticle("Python programming language");
        pageObject.clickPythonArticleLink();
        pageObject.verifyPythonArticleLoaded();
        pageObject.verifyArticleStructureAndPresentation();
        pageObject.navigateUsingTableOfContents();
        pageObject.clickInternalLinks();
        pageObject.takeScreenshot("python-article-learning.png");
    }

    @Test
    public void testNegativeNoSearchTerm() {
        ViewArticleOnWikipediaPage pageObject = new ViewArticleOnWikipediaPage(this);
        pageObject.navigateToWikipedia();
        pageObject.clickSearchWithoutTerm();
        pageObject.verifyNoResultsFound();
        pageObject.takeScreenshot("no-search-results.png");
    }

    @Test
    public void testNegativeWhenVeryLongTextIsProvided() {
        ViewArticleOnWikipediaPage pageObject = new ViewArticleOnWikipediaPage(this);
        pageObject.navigateToWikipedia();
        pageObject.enterVeryLongTextInSearchField();
        pageObject.clickSearchIcon();
        page.waitForLoadState(LoadState.NETWORKIDLE);
        org.testng.Assert.assertTrue(page.title().contains("An error has occurred while searching: Search request is longer than the maximum allowed length - Search results - Wikipedia"));
        pageObject.takeScreenshot("negative-very-long-text.png");
    }

    @Test
    public void testNegativeWhenInvalidTextIsProvided() {
        ViewArticleOnWikipediaPage pageObject = new ViewArticleOnWikipediaPage(this);
        pageObject.navigateToWikipedia();
        pageObject.enterInvalidTextInSearchField();
        pageObject.clickSearchIcon();
        page.waitForLoadState(LoadState.NETWORKIDLE);
        org.testng.Assert.assertTrue(page.title().contains("There were no results matching the query - Search results - Wikipedia"));
        pageObject.takeScreenshot("negative-invalid-text.png");
    }
}
