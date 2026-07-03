package com.automation.tests;

import com.automation.base.BaseTestManager;
import com.automation.pages.ViewArticlePage;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import org.testng.annotations.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class ViewArticleTest extends BaseTestManager {

    @Test
    public void testHappyPathPrimaryFlow() {
        ViewArticlePage pageObject = new ViewArticlePage(this);
        pageObject.navigateToWikipedia();
        pageObject.searchForArticle("Baby Doll");
        assertThat(pageObject.page.locator("#firstHeading")).containsText("Baby Doll");
        pageObject.takeScreenshot("happy-path-primary-flow.png");
    }

    @Test
    public void testValidationInvalidInputRejected() {
        ViewArticlePage pageObject = new ViewArticlePage(this);
        pageObject.navigateToWikipedia();
        pageObject.searchForArticle("!@#$%^&*()");
        assertThat(pageObject.page.locator("#firstHeading")).not().containsText("!@#$%^&*()");
        pageObject.takeScreenshot("validation-invalid-input.png");
    }

    @Test
    public void testBoundaryEdgeValuesHandled() {
        ViewArticlePage pageObject = new ViewArticlePage(this);
        pageObject.navigateToWikipedia();
        String longArticleName = "ThisIsAVeryLongArticleNameThatExceedsTheMaximumLengthAllowedForSearchQueries";
        pageObject.searchForArticle(longArticleName);
        assertThat(pageObject.page.locator("#firstHeading")).not().containsText(longArticleName);
        pageObject.takeScreenshot("boundary-edge-values.png");
    }
}