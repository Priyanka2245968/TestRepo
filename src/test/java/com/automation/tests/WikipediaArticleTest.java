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
        pageObject.searchForArticle("Python programming language");
        pageObject.openArticle("Python (programming language)");
        pageObject.verifyArticleDisplayed("Python (programming language) - Wikipedia");
        pageObject.takeScreenshot("happy-path-search-and-view-article.png");
    }

    @Test
    public void testNegativeInvalidInputEmptySearchField() {
        WikipediaArticlePage pageObject = new WikipediaArticlePage(this);
        pageObject.navigateToWikipedia();
        pageObject.searchForArticle("");
        assertThat(getPage()).hasTitle("Wikipedia, the free encyclopedia");
        pageObject.takeScreenshot("negative-invalid-input-empty-search-field.png");
    }

    @Test
    public void testNegativeBoundaryMaximumSearchLength() {
        WikipediaArticlePage pageObject = new WikipediaArticlePage(this);
        String longSearchTerm = "a".repeat(500);
        pageObject.navigateToWikipedia();
        pageObject.searchForArticle(longSearchTerm);
        assertThat(getPage()).hasTitle("Wikipedia, the free encyclopedia");
        pageObject.takeScreenshot("negative-boundary-maximum-search-length.png");
    }
}