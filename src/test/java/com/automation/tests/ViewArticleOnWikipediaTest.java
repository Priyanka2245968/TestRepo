package com.automation.tests;

import com.automation.base.BaseTestManager;
import com.automation.pages.ViewArticleOnWikipediaPage;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class ViewArticleOnWikipediaTest extends BaseTestManager {

    private static final String WIKIPEDIA_URL = "https://www.wikipedia.org/";
    private static final String SEARCH_TERM = "HTML";
    private static final String ARTICLE_LINK_SELECTOR = "a[href='/wiki/HTML']"; 

    @Test
    public void testHappyPathSearchAndViewArticle() {
        ViewArticleOnWikipediaPage pageObject = new ViewArticleOnWikipediaPage(this);
        pageObject.navigateToWikipediaHomepage(WIKIPEDIA_URL);
        pageObject.searchForTerm(SEARCH_TERM);
        pageObject.clickSearchButton();
        pageObject.clickSearchResultLink(ARTICLE_LINK_SELECTOR);
        pageObject.takeScreenshot("wikipedia-article.png");
    }

    @Test
    public void testNegativeSearchTermExceedsMaxLength() {
        ViewArticleOnWikipediaPage pageObject = new ViewArticleOnWikipediaPage(this);
        pageObject.navigateToWikipediaHomepage(WIKIPEDIA_URL);
        String longSearchTerm = "A text with more than 500 characters".repeat(5);
        pageObject.searchForTerm(longSearchTerm);
        pageObject.clickSearchButton();
        assertTrue(pageObject.verifyErrorMessageDisplayed("An error has occurred while searching: Search request is longer than the maximum allowed length"), "Error message not displayed");
    }

    @Test
    public void testNegativeInvalidSearchTerm() {
        ViewArticleOnWikipediaPage pageObject = new ViewArticleOnWikipediaPage(this);
        pageObject.navigateToWikipediaHomepage(WIKIPEDIA_URL);
        pageObject.searchForTerm("!@#$%^&*()");
        pageObject.clickSearchButton();
        assertTrue(pageObject.verifyNoResultsDisplayed(), "No results message not displayed");
    }
}