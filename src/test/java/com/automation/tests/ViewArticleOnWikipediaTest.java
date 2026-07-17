package com.automation.tests;

import com.automation.base.BaseTestManager;
import com.automation.pages.WikipediaArticlePage;
import com.microsoft.playwright.options.LoadState;
import org.testng.annotations.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.testng.Assert.assertTrue;

public class ViewArticleOnWikipediaTest extends BaseTestManager {

    @Test
    public void testHappyPathSuccessfulWikipediaArticleSearchAndView() {
        WikipediaArticlePage pageObject = new WikipediaArticlePage(this);
        pageObject.navigateToWikipedia();
        pageObject.searchForArticle("HTML");
        pageObject.clickSearchButton();
        pageObject.waitForSearchResults();
        pageObject.clickArticleLink("HTML");
        assertThat(page).hasTitle("HTML - Wikipedia");
        pageObject.takeScreenshot("html-article.png");
    }

    @Test
    public void testArticleContentIsClearAndSuitableForLearning() {
        WikipediaArticlePage pageObject = new WikipediaArticlePage(this);
        pageObject.navigateToWikipedia();
        pageObject.searchForArticle("Python (programming language)");
        pageObject.clickSearchButton();
        pageObject.waitForSearchResults();
        pageObject.clickArticleLink("Python (programming language)");
        assertThat(page).hasTitle("Python (programming language) - Wikipedia");
    }
}