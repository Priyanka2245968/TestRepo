package com.automation.tests;

import com.automation.base.BaseTestManager;
import com.automation.pages.ViewArticleOnWikipediaPage;
import com.microsoft.playwright.options.LoadState;
import org.testng.annotations.Test;

public class ViewArticleOnWikipediaTest extends BaseTestManager {

    @Test
    public void testHappyPathSearchAndViewArticle() {
        ViewArticleOnWikipediaPage pageObject = new ViewArticleOnWikipediaPage(this);
        pageObject.navigateToWikipediaHomepage();
        pageObject.searchForTerm("Python programming language");
        pageObject.clickSearchButton();
        pageObject.waitForSearchResultsToLoad();
        pageObject.clickPythonProgrammingLanguageLink();
        pageObject.waitForArticleToLoad();
        pageObject.verifyArticleContentVisible();
        pageObject.takeScreenshot("python-article.png");
    }

    @Test
    public void testNegativeBlankSearchInput() {
        ViewArticleOnWikipediaPage pageObject = new ViewArticleOnWikipediaPage(this);
        pageObject.navigateToWikipediaHomepage();
        pageObject.clickSearchButton();
        pageObject.waitForNoResultsPage();
        pageObject.verifyNoResultsPageVisible();
        pageObject.takeScreenshot("no-results.png");
    }

    @Test
    public void testNegativeTooLongSearchInput() {
        ViewArticleOnWikipediaPage pageObject = new ViewArticleOnWikipediaPage(this);
        pageObject.navigateToWikipediaHomepage();
        pageObject.searchForTerm("This is a very long search input that exceeds the maximum allowed length of 500 characters. This is a very long search input that exceeds the maximum allowed length of 500 characters. This is a very long search input that exceeds the maximum allowed length of 500 characters.");
        pageObject.clickSearchButton();
        pageObject.waitForErrorMessageToLoad();
        pageObject.verifyErrorMessageVisible();
        pageObject.takeScreenshot("error-message.png");
    }

    @Test
    public void testNegativeSearchInputWithNoMatchingArticle() {
        System.out.println("✨ Starting Negative — Search input with no matching article");
        ViewArticleOnWikipediaPage pageObject = new ViewArticleOnWikipediaPage(this);

        pageObject.navigateToWikipediaHomepage();
        pageObject.searchForTerm("akjshfkajhfkajhfhdfdf");
        pageObject.clickSearchButton();

        pageObject.waitForNoResultsPage();

        pageObject.verifyNoResultsMessage();
        pageObject.verifyNoArticleLinksDisplayed();

        pageObject.takeScreenshot("no-results-" + System.currentTimeMillis() + ".png");
        System.out.println("✅ Test completed successfully!");
    }
}
