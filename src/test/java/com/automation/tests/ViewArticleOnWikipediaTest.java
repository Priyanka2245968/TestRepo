package com.automation.tests;

import com.automation.base.BaseTestManager;
import com.automation.pages.ViewArticleOnWikipediaPage;
import com.microsoft.playwright.options.LoadState;
import org.testng.annotations.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class ViewArticleOnWikipediaTest extends BaseTestManager {

    @Test
    public void testHappyPathSearchAndViewWikipediaArticle() {
        ViewArticleOnWikipediaPage pageObject = new ViewArticleOnWikipediaPage(this);
        pageObject.navigateToWikipedia();
        pageObject.fillSearchField("HTML");
        pageObject.clickSearchButton();
        pageObject.waitForLoadState(LoadState.NETWORKIDLE);
        pageObject.clickHTMLLinkInSearchResults();
        pageObject.waitForLoadState(LoadState.NETWORKIDLE);
        pageObject.takeScreenshot("wikipedia-html-article.png");
    }

    @Test
    public void testWikipediaIsAccessibleAnonymously() {
        ViewArticleOnWikipediaPage pageObject = new ViewArticleOnWikipediaPage(this);
        pageObject.navigateToWikipedia();
        pageObject.waitForLoadState(LoadState.NETWORKIDLE);
        assertThat(testManager.getPage()).hasTitle("Wikipedia");
        pageObject.takeScreenshot("wikipedia-homepage.png");
    }

    @Test
    public void testNegativeNoSearchTermProvided() {
        ViewArticleOnWikipediaPage pageObject = new ViewArticleOnWikipediaPage(this);
        pageObject.navigateToWikipedia();
        pageObject.clickSearchButton();
        pageObject.waitForLoadState(LoadState.NETWORKIDLE);
        pageObject.takeScreenshot("wikipedia-no-search-term.png");
    }
}
