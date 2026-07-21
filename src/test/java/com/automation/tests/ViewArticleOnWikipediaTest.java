package com.automation.tests;

import com.automation.base.BaseTestManager;
import com.automation.pages.WikipediaArticlePage;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import com.microsoft.playwright.options.LoadState;
import org.testng.annotations.Test;

public class ViewArticleOnWikipediaTest extends BaseTestManager {

    @Test
    public void testHappyPathSearchAndViewArticle() {
        WikipediaArticlePage pageObject = new WikipediaArticlePage(this);
        pageObject.navigateToWikipedia();
        pageObject.searchForTerm("HTML");
        pageObject.waitForSearchResults();
        pageObject.clickSearchResult("HTML");
        pageObject.waitForArticleLoad();
        PlaywrightAssertions.assertThat(getPage()).hasTitle("HTML - Wikipedia");
        pageObject.takeScreenshot("html-article.png");
    }

    @Test
    public void testArticleContentReadable() {
        WikipediaArticlePage pageObject = new WikipediaArticlePage(this);
        pageObject.navigateToWikipedia();
        pageObject.searchForTerm("HTML");
        pageObject.waitForSearchResults();
        pageObject.clickSearchResult("HTML");
        pageObject.waitForArticleLoad();
        PlaywrightAssertions.assertThat(pageObject.getArticleContent()).isVisible();
        pageObject.takeScreenshot("html-article-content.png");
    }

    @Test
    public void testNoTextInSearchBar() {
        WikipediaArticlePage pageObject = new WikipediaArticlePage(this);
        pageObject.navigateToWikipedia();
        // Add assertions to check the search bar is empty
    }
}