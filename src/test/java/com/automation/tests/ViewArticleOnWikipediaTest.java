package com.automation.tests;

import com.automation.base.BaseTestManager;
import com.automation.pages.WikipediaArticlePage;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class ViewArticleOnWikipediaTest extends BaseTestManager {

    @Test
    public void testHappyPathPrimaryFlow() {
        WikipediaArticlePage pageObject = new WikipediaArticlePage(this);
        pageObject.navigateToArticle("Baby_Doll");
        assertThat(pageObject.articleTitle).hasText("Baby Doll");
        pageObject.takeScreenshot("happy-path-" + System.currentTimeMillis() + ".png");
    }

    @Test
    public void testValidationInvalidInputRejected() {
        WikipediaArticlePage pageObject = new WikipediaArticlePage(this);
        pageObject.navigateToArticle("!@#$%^&*()");
        String actualTitle = pageObject.getArticleTitle();
        Assert.assertFalse(actualTitle.contains("!@#$%^&*()"));
        pageObject.takeScreenshot("invalid-input-" + System.currentTimeMillis() + ".png");
    }

    @Test
    public void testBoundaryEdgeValuesHandled() {
        WikipediaArticlePage pageObject = new WikipediaArticlePage(this);
        pageObject.navigateToArticle("A");
        assertThat(pageObject.articleTitle).hasText("A");
        pageObject.takeScreenshot("edge-value-" + System.currentTimeMillis() + ".png");
    }
}