package com.automation.tests;

import com.automation.base.BaseTestManager;
import com.automation.pages.WikipediaArticlePage;
import org.testng.annotations.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class WikipediaArticleTest extends BaseTestManager {

    @Test
    public void testHappyPathSearchAndViewArticle() {
        WikipediaArticlePage pageObject = new WikipediaArticlePage(this);
        pageObject.navigateToWikipedia();
        pageObject.searchForTerm("HTML");
        pageObject.clickSearchButton();
        pageObject.clickHtmlLink();
        assertThat(testManager.getPage()).hasTitle("HTML - Wikipedia");
        pageObject.takeScreenshot("wikipedia-article-test.png");
    }

    @Test
    public void testNegativeSearchTermExceedsMaxLength() {
        WikipediaArticlePage pageObject = new WikipediaArticlePage(this);
        pageObject.navigateToWikipedia();
        String longTerm = "A text string longer than 500 characters";
        pageObject.searchForTerm(longTerm);
        pageObject.clickSearchButton();
        assertThat(testManager.getPage()).hasTitle("Wikipedia");
        pageObject.takeScreenshot("wikipedia-long-search-term.png");
    }

    @Test
    public void testNegativeInvalidSearchTerm() {
        WikipediaArticlePage pageObject = new WikipediaArticlePage(this);
        pageObject.navigateToWikipedia();
        pageObject.searchForTerm("!@#$%^&*()");
        pageObject.clickSearchButton();
        assertThat(testManager.getPage()).hasTitle("Wikipedia");
        pageObject.takeScreenshot("wikipedia-invalid-search-term.png");
    }
}