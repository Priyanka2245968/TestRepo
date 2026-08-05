package com.automation.tests;

import com.automation.base.BaseTestManager;
import com.automation.pages.ViewArticleOnWikipediaPage;
import com.automation.pages.WikipediaArticlePage;
import com.microsoft.playwright.options.LoadState;
import org.testng.annotations.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class ViewArticleOnWikipediaTest extends BaseTestManager {

    @Test
    public void testHappyPathViewArticleOnWikipedia() {
        ViewArticleOnWikipediaPage pageObject = new ViewArticleOnWikipediaPage(this);
        pageObject.navigateToWikipediaHomepage();
        pageObject.searchForTerm("HTML Tables");
        pageObject.clickSearchButton();
        pageObject.waitForSearchResultsPage();
        pageObject.clickFirstSearchResult();
        pageObject.waitForArticlePage();
        pageObject.takeScreenshot("wikipedia-article-page.png");
    }

    @Test
    public void testWikipediaAccessibleWithoutLogin() {
        ViewArticleOnWikipediaPage pageObject = new ViewArticleOnWikipediaPage(this);
        pageObject.navigateToWikipediaHomepage();
        assertThat(getPage()).hasTitle("Wikipedia");
        pageObject.verifyHomepageContentAccessible();
    }

    @Test
    public void testNegativeNoSearchTerm() {
        ViewArticleOnWikipediaPage pageObject = new ViewArticleOnWikipediaPage(this);
        pageObject.navigateToWikipediaHomepage();
        pageObject.clickSearchButton();
        pageObject.verifyNoSearchTermMessage();
    }

    @Test
    public void testHappyPath() {
        WikipediaArticlePage pageObject = new WikipediaArticlePage(this);
        pageObject.navigateToWikipedia();
        pageObject.searchForTerm("HTML Table");
        pageObject.clickSearchButton();
        pageObject.clickFirstSearchResult();
        pageObject.takeScreenshot("happy-path.png");
    }

    @Test
    public void testNegativeSearchTermExceeds500Characters() {
        WikipediaArticlePage pageObject = new WikipediaArticlePage(this);
        pageObject.navigateToWikipedia();
        String longTerm = "a".repeat(501);
        pageObject.searchForTerm(longTerm);
        pageObject.clickSearchButton();
        pageObject.takeScreenshot("negative-search-term-exceeds-500.png");
    }

    @Test
    public void testNegativeSearchTermWithNoMatchingArticle() {
        WikipediaArticlePage pageObject = new WikipediaArticlePage(this);
        pageObject.navigateToWikipedia();
        pageObject.searchForTerm("azbycxnvqwer");
        pageObject.clickSearchButton();
        pageObject.takeScreenshot("negative-search-term-no-match.png");
    }
}