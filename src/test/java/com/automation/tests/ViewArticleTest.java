package com.automation.tests;

import com.automation.base.BaseTestManager;
import com.automation.pages.ViewArticlePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ViewArticleTest extends BaseTestManager {

    @Test
    public void testHappyPathPrimaryFlow() {
        ViewArticlePage pageObject = new ViewArticlePage(this);
        pageObject.navigateToWikipedia();
        pageObject.searchForArticle("Baby Doll");
        String articleTitle = pageObject.getArticleTitle();
        Assert.assertTrue(articleTitle.contains("Baby Doll"));
        pageObject.takeScreenshot("happy-path-primary-flow.png");
    }

    @Test
    public void testValidationInvalidInputRejected() {
        ViewArticlePage pageObject = new ViewArticlePage(this);
        pageObject.navigateToWikipedia();
        pageObject.searchForArticle("!@#$%^&*()");
        String articleTitle = pageObject.getArticleTitle();
        Assert.assertFalse(articleTitle.contains("!@#$%^&*()"));
        pageObject.takeScreenshot("validation-invalid-input.png");
    }

    @Test
    public void testBoundaryEdgeValuesHandled() {
        ViewArticlePage pageObject = new ViewArticlePage(this);
        pageObject.navigateToWikipedia();
        String longArticleName = "ThisIsAVeryLongArticleNameThatExceedsTheMaximumLengthAllowedForSearchQueries";
        pageObject.searchForArticle(longArticleName);
        String articleTitle = pageObject.getArticleTitle();
        Assert.assertFalse(articleTitle.contains(longArticleName));
        pageObject.takeScreenshot("boundary-edge-values.png");
    }
}
