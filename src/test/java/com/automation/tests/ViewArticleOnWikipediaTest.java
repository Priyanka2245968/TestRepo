package com.automation.tests;

import com.automation.base.BaseTestManager;
import com.automation.pages.ViewArticleOnWikipediaPage;
import com.microsoft.playwright.Locator;
import org.testng.annotations.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class ViewArticleOnWikipediaTest extends BaseTestManager {

    @Test
    public void testHappyPathViewArticleOnWikipedia() {
        ViewArticleOnWikipediaPage pageObject = new ViewArticleOnWikipediaPage(this);
        pageObject.navigateToWikipedia();
        pageObject.searchForArticle("HTML");
        pageObject.clickSearchButton();
        pageObject.clickArticleLink();
        pageObject.takeScreenshot("wikipedia-article.png");
    }

    @Test
    public void testWikipediaAccessibleToAnonymousUsers() {
        ViewArticleOnWikipediaPage pageObject = new ViewArticleOnWikipediaPage(this);
        pageObject.navigateToWikipedia();
        Locator homepageElement = pageObject.getHomepageElement();
        assertThat(homepageElement).isVisible();
        pageObject.takeScreenshot("wikipedia-homepage.png");
    }

    @Test
    public void testNoArticleInSearchBar() {
        ViewArticleOnWikipediaPage pageObject = new ViewArticleOnWikipediaPage(this);
        pageObject.navigateToWikipedia();
        pageObject.clickSearchButton();
        pageObject.waitForTimeout(2000); // Wait for the page to load
        assertThat(getPage()).hasURL("https://en.wikipedia.org/wiki/Special:Search?search=&go=Go");
        pageObject.takeScreenshot("wikipedia-no-search.png");
    }

    @Test
    public void testViewArticleOnWikipedia() {
        ViewArticleOnWikipediaPage pageObject = new ViewArticleOnWikipediaPage(this);
        pageObject.navigateToWikipedia();
        pageObject.searchForArticle("HTML");
        pageObject.viewArticle();
        pageObject.takeScreenshot("wikipedia-article.png");
    }

    @Test
    public void testLongSearchQuery() {
        ViewArticleOnWikipediaPage pageObject = new ViewArticleOnWikipediaPage(this);
        pageObject.navigateToWikipedia();
        String longQuery = "A".repeat(501);
        pageObject.searchForArticle(longQuery);
        pageObject.clickSearchButton();
        pageObject.waitForTimeout(2000);
        assertThat(getPage()).hasURL("https://en.wikipedia.org/wiki/Special:Search?search=AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA&go=Go&ns0=1");
        pageObject.takeScreenshot("long-query-error.png");
    }

    @Test
    public void testInvalidSearchQuery() {
        ViewArticleOnWikipediaPage pageObject = new ViewArticleOnWikipediaPage(this);
        pageObject.navigateToWikipedia();
        pageObject.searchForArticle("qwerty123!@#");
        pageObject.clickSearchButton();
        pageObject.waitForTimeout(2000);
        assertThat(getPage()).hasURL("https://en.wikipedia.org/wiki/Special:Search?search=qwerty123%21%40%23&go=Go&ns0=1");
        pageObject.takeScreenshot("invalid-query-error.png");
    }
}