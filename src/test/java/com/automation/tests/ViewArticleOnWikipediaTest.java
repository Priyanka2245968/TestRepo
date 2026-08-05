package com.automation.tests;

import com.automation.base.BaseTestManager;
import com.automation.pages.ViewArticleOnWikipediaPage;
import org.testng.annotations.Test;

public class ViewArticleOnWikipediaTest extends BaseTestManager {

    @Test
    public void testHappyPathSearchAndViewArticle() {
        ViewArticleOnWikipediaPage pageObject = new ViewArticleOnWikipediaPage(this);
        pageObject.navigateToWikipediaHomepage();
        pageObject.searchForTerm("HTML");
        pageObject.clickSearchButton();
        pageObject.clickSearchResultLink("a[href='/wiki/HTML']");
        pageObject.takeScreenshot("wikipedia-article.png");
    }

    @Test
    public void testNegativeSearchTermExceedsMaxLength() {
        ViewArticleOnWikipediaPage pageObject = new ViewArticleOnWikipediaPage(this);
        pageObject.navigateToWikipediaHomepage();
        String longSearchTerm = "A text with more than 500 characters" + "A text with more than 500 characters" + "A text with more than 500 characters" + "A text with more than 500 characters" + "A text with more than 500 characters";
        pageObject.searchForTerm(longSearchTerm);
        pageObject.clickSearchButton();
        pageObject.verifyErrorMessageDisplayed("An error has occurred while searching: Search request is longer than the maximum allowed length");
    }

    @Test
    public void testNegativeInvalidSearchTerm() {
        ViewArticleOnWikipediaPage pageObject = new ViewArticleOnWikipediaPage(this);
        pageObject.navigateToWikipediaHomepage();
        pageObject.searchForTerm("!@#$%^&*()_+");
        pageObject.clickSearchButton();
        pageObject.verifyErrorMessageDisplayed("There were no results matching the query");
    }
}
