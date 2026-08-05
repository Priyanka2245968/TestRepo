package com.automation.tests;

import com.automation.base.BaseTestManager;
import com.automation.pages.WikipediaArticlePage;
import com.microsoft.playwright.options.LoadState;
import org.testng.annotations.Test;

public class WikipediaArticleTest extends BaseTestManager {

    @Test
    public void testHappyPathSearchAndViewArticle() {
        WikipediaArticlePage pageObject = new WikipediaArticlePage(this);
        page.navigate("https://www.wikipedia.org/");
        pageObject.fillSearchField("HTML");
        pageObject.clickSearchButton();
        page.waitForLoadState(LoadState.NETWORKIDLE);
        pageObject.clickArticleLink("HTML");
        pageObject.takeScreenshot("wikipedia-article-test.png");
    }

    @Test
    public void testArticleContentIsReadable() {
        WikipediaArticlePage pageObject = new WikipediaArticlePage(this);
        page.navigate("https://www.wikipedia.org/");
        pageObject.fillSearchField("HTML");
        pageObject.clickSearchButton();
        page.waitForLoadState(LoadState.NETWORKIDLE);
        pageObject.clickArticleLink("HTML");
        pageObject.verifyArticleContentIsReadable();
        pageObject.takeScreenshot("wikipedia-article-readable-test.png");
    }

    @Test
    public void testNegativeNoSearchTerm() {
        WikipediaArticlePage pageObject = new WikipediaArticlePage(this);
        page.navigate("https://www.wikipedia.org/");
        pageObject.clickSearchButton();
        page.waitForLoadState(LoadState.NETWORKIDLE);
        pageObject.verifyNoSearchResultsDisplayed();
        pageObject.takeScreenshot("wikipedia-no-search-term-test.png");
    }
}
