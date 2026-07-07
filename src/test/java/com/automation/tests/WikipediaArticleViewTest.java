package com.automation.tests;

import com.automation.base.BaseTestManager;
import com.automation.pages.WikipediaArticleViewPage;
import com.microsoft.playwright.options.LoadState;
import org.testng.annotations.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class WikipediaArticleViewTest extends BaseTestManager {

    @Test
    public void testHappyPathSearchAndViewArticle() {
        WikipediaArticleViewPage pageObject = new WikipediaArticleViewPage(this);
        pageObject.navigateToWikipedia();
        pageObject.searchForArticle("Python programming language");
        pageObject.clickSearchButton();
        pageObject.clickPythonProgrammingLanguageLink();
        assertThat(page).hasTitle("Python (programming language) - Wikipedia");
        pageObject.takeScreenshot("python-article.png");
    }

    @Test
    public void testWebsiteAccessibleToAnonymousUsers() {
        WikipediaArticleViewPage pageObject = new WikipediaArticleViewPage(this);
        pageObject.navigateToWikipedia();
        assertThat(page).hasTitle("Wikipedia");
        pageObject.clickRandomArticleLink();
        String randomArticleTitle = page.title();
        assertThat(page).hasTitle(randomArticleTitle);
        org.testng.Assert.assertFalse(randomArticleTitle.equals("Wikipedia"));
        pageObject.takeScreenshot("random-article.png");
    }

    @Test
    public void testNoSearchResultsForBlankSearch() {
        WikipediaArticleViewPage pageObject = new WikipediaArticleViewPage(this);
        pageObject.navigateToWikipedia();
        pageObject.clickSearchButton();
        assertThat(page).hasTitle("Search - Wikipedia");
        String noResultsMessage = pageObject.getNoResultsMessage();
        org.testng.Assert.assertTrue(noResultsMessage.contains("There were no results matching the query"));
        pageObject.takeScreenshot("no-results.png");
    }
}