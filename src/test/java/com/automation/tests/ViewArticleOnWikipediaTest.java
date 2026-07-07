package com.automation.tests;

import com.automation.base.BaseTestManager;
import com.automation.pages.ViewArticleOnWikipediaPage;
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
        pageObject.searchForArticle("");
        pageObject.verifyNoSearchResultsLoaded();
    }
}