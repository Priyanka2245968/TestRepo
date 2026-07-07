package com.automation.tests;

import com.automation.base.BaseTestManager;
import com.automation.pages.ViewArticlePage;
import org.testng.annotations.Test;

public class ViewArticleTest extends BaseTestManager {

    @Test
    public void testHappyPathSearchAndViewArticle() {
        ViewArticlePage pageObject = new ViewArticlePage(this);
        pageObject.navigateToWikipedia();
        pageObject.searchForArticle("Python programming language");
        pageObject.verifySearchResultsLoaded();
        pageObject.clickArticleLink("Python");
        pageObject.verifyArticleLoaded("Python");
    }

    @Test
    public void testViewArticleWithoutLogin() {
        ViewArticlePage pageObject = new ViewArticlePage(this);
        pageObject.navigateToWikipedia();
        pageObject.searchForArticle("HTML");
        pageObject.clickArticleLink("HTML");
        pageObject.verifyArticleLoaded("HTML");
    }

    @Test
    public void testNoSearchInput() {
        ViewArticlePage pageObject = new ViewArticlePage(this);
        pageObject.navigateToWikipedia();
        pageObject.clickSearchButton();
        pageObject.verifyNoSearchResults();
    }
}
