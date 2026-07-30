package com.automation.tests;

import com.automation.base.BaseTestManager;
import com.automation.pages.WikipediaArticlePage;
import org.testng.annotations.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class WikipediaArticleTest extends BaseTestManager {

    @Test
    public void testViewArticleOnWikipedia() {
        WikipediaArticlePage pageObject = new WikipediaArticlePage(this);
        pageObject.navigateToWikipedia();
        pageObject.searchForArticle("HTML");
        pageObject.viewArticle();
        pageObject.verifyArticleLoaded("HTML - Wikipedia");
        pageObject.takeScreenshot("wikipedia-article.png");
    }

    @Test
    public void testSearchInputExceeds500Characters() {
        WikipediaArticlePage pageObject = new WikipediaArticlePage(this);
        pageObject.navigateToWikipedia();
        StringBuilder longSearchTerm = new StringBuilder();
        for (int i = 0; i < 501; i++) {
            longSearchTerm.append("a");
        }
        pageObject.searchForArticle(longSearchTerm.toString());
        pageObject.takeScreenshot("search-input-exceeds-500.png");
    }

    @Test
    public void testSearchInputReturnsNoResults() {
        WikipediaArticlePage pageObject = new WikipediaArticlePage(this);
        pageObject.navigateToWikipedia();
        pageObject.searchForArticle("asdfghjklqwertyuiop");
        assertThat(getPage().locator(".no-results")).isVisible();
        pageObject.takeScreenshot("search-no-results.png");
    }
}
